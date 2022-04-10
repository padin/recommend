package com.wyizd.recommend.security;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.enums.ROLE_TYPE;
import com.wyizd.recommend.mapper.UserMapper;
import com.wyizd.recommend.utils.JwtUtils;
import com.wyizd.recommend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserMapper um;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();

		User user = um.selectById(authentication.getName());
		user.setLastLoginTime(LocalDateTime.now());
		um.updateById(user);


		// 生成jwt，并放置到请求头中
		String jwt = jwtUtils.generateToken(authentication.getName());
		response.setHeader(jwtUtils.getHeader(), jwt);

		Result result = Result.succ(MapUtil.builder()
				.put("id", user.getId().toString())
				.put("username", user.getUserName())
				.put("role", ROLE_TYPE.gen(user.getRoleType()))
				.put("oid", user.getOrganazationId().toString())
				.map());

		outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

		outputStream.flush();
		outputStream.close();
	}

}
