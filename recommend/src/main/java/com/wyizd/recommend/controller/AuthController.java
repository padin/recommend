package com.wyizd.recommend.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.Producer;
import com.wyizd.recommend.common.SMSCodePool;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.enums.ROLE_TYPE;
import com.wyizd.recommend.service.UserService;
import com.wyizd.recommend.utils.RandomUtil;
import com.wyizd.recommend.utils.Result;
import com.wyizd.recommend.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	Producer producer;
	@Autowired
	UserService userService;



	/**
	 * 获取用户信息接口
	 * @param principal
	 * @return
	 */
	@PostMapping("/userInfo")
	public Result userInfo(Principal principal) {

//		LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
//		ulqw.eq(User::getPhoneNumber,principal.getName());
		User sysUser = userService.getById(principal.getName());

		return Result.succ(MapUtil.builder()
				.put("id", sysUser.getId().toString())
				.put("userName", sysUser.getUserName())
				.put("realName", sysUser.getRealName())
				.put("phoneNumber", sysUser.getPhoneNumber())
				.put("erCodeAddress", sysUser.getErCodeAddress())
				.put("role", ROLE_TYPE.gen(sysUser.getRoleType()))
				.put("oid", sysUser.getOrganazationId().toString())
				.map()
		);
	}

	/**
	 * 获取验证码
	 * @return
	 */
	@PostMapping("/getValiCode")
	public Result getValiCode(String phoneNumber) {
		if (phoneNumber.isEmpty()){
			return Result.fail("手机号不正确");
		}
		try {
			userService.checkPhoneExist(phoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("该手机号以注册");
		}
		String anInt = RandomUtil.getInt(6);
		SMSCodePool.setCode(phoneNumber,anInt);
		try {
//			SMSUtil.send(phoneNumber);
			return Result.succ(SMSCodePool.getCode(phoneNumber));
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail(e.getMessage());
		}
	}

	/**
	 * 验查验证码
	 * @return
	 */
	@PostMapping("/checkValiCode")
	public Result checkValiCode(String phoneNumber,String code) {
		String code1 = SMSCodePool.getCode(phoneNumber);
		if (code.equals(code1)){
			return Result.succ();
		}else{
			return Result.fail("验证失败");
		}
	}





}
