package com.guru84.todo.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guru84.todo.app.repositories.UserRepository;
import com.guru84.todo.app.service.UserService;
import com.guru84.todo.app.shared.dto.UserDto;
import com.guru84.todo.app.ui.model.response.ErrorMessages;
import com.guru84.todo.app.utils.Utils;
import com.guru84.todo.app.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		
		if (userRepository.findByEmail(user.getEmail()) != null ) {
			throw new RuntimeException("Record already exist");
		}
		
		
		//UserEntity userEntity = new UserEntity();
		//BeanUtils.copyProperties(user, userEntity);
		ModelMapper modelMapper  = new ModelMapper();
		User userEntity = modelMapper.map(user, User.class);
		
		userEntity.setPassword(
				encoder.encode(user.getPassword()));
		
		String publicUserId = utils.generateUserId(30);
		//userEntity.setUserId(publicUserId);
		
		User storedUserDetails = userRepository.save(userEntity);
		
		//UserDto returnValue = new UserDto();
		//BeanUtils.copyProperties(storedUserDetails, returnValue);
		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
		return returnValue;
	}
	
	@Override 
	public UserDto getUser(String email) throws UsernameNotFoundException{
		User entity = userRepository.findByUsername(email);
		
		if (entity == null) throw new UsernameNotFoundException(email);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(entity, returnValue);
		return returnValue;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//	User userEntity = userRepository.findByUsername(username);
		
		//if (userEntity == null) throw new UsernameNotFoundException(username);
		return  null;//new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		User entity = userRepository.findByUsername(userId);
		
		if(entity == null) throw new UsernameNotFoundException(userId); // Similar to updateUser method , we can use UserServiceException
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(entity, returnValue);
		
		return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		
		User entity = userRepository.findByUsername(userId);
		
		//if (entity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		entity.setName(user.getFirstName());
		User updatedEntity = userRepository.save(entity);
		
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(updatedEntity, returnValue);
		
		
		return returnValue;
	}

	@Override
	public Boolean deleteUserByUserId(String userId) {
		
		User entity = userRepository.findByUsername(userId);
		//if (entity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(entity);
		
		return true;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		//Iterable<UserEntity> entityUsers = userRepository.findAll();
		if (page > 0) page--;
		
		List<UserDto> returnvalue = new ArrayList<UserDto>();
		
//		Pageable pageableRequest = PageRequest.of(page, limit);
//		
//		Page<User> pagedUsers = userRepository.findAll(pageableRequest);
//		
//		List<User> users = pagedUsers.getContent();
//		
//		for(User userEntity : users) {
//			UserDto oneUser = new UserDto();
//			BeanUtils.copyProperties(userEntity, oneUser);
//			returnvalue.add(oneUser);
//		}
		
		
		return returnvalue;
	}

}
