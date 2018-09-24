package com.rdp.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.rdp.dto.UserDto;
import com.rdp.model.User;
import com.rdp.req.UserSearchReq;

public interface UserService {
	List<UserDto> search(UserSearchReq dto);

	User findUserByUserName(String username);

	User findUsersByEmail(String email);

	List<SimpleGrantedAuthority> getAuthorityByUserId(int userId);
}