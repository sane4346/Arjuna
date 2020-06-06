package com.guru84.todo.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.guru84.todo.app.shared.dto.UserDto;


public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	Boolean deleteUserByUserId(String userId);
	//Pagination
	List<UserDto> getUsers(int page, int limit);
}
