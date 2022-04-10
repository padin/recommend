package com.wyizd.recommend.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.enums.ROLE_TYPE;
import com.wyizd.recommend.mapper.UserMapper;
import com.wyizd.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
		ulqw.eq(User::getPhoneNumber,username);
		User sysUser = userMapper.selectOne(ulqw);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名或密码不正确");
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(Objects.requireNonNull(ROLE_TYPE.gen(sysUser.getRoleType())).toString());
		return new AccountUser(sysUser.getId(), sysUser.getId().toString(), sysUser.getPassword(), grantedAuthorities);
	}

	public Collection<? extends GrantedAuthority> getUserAuthority(Long id) {
		LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
		ulqw.eq(User::getPhoneNumber,id);
		User sysUser = userMapper.selectOne(ulqw);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(Objects.requireNonNull(ROLE_TYPE.gen(sysUser.getRoleType())).toString());
	}
}
