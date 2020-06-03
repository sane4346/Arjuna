package com.guru84.todo.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru84.todo.app.entity.TodoEntity;
import com.guru84.todo.app.entity.UserEntity;
import com.guru84.todo.app.repositories.TodoRepository;
import com.guru84.todo.app.repositories.UserRepository;
import com.guru84.todo.app.service.TodoService;
import com.guru84.todo.app.shared.dto.TodoDto;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TodoRepository todoRepository;
	
	@Override
	public List<TodoDto> getTodoByUserId(String userId) {
		List<TodoDto> returnValue = new ArrayList<>();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) return returnValue; // Similar to updateUser method , we can use UserServiceException
		
		ModelMapper modelMapper = new ModelMapper();
		
		Iterable<TodoEntity> todos = todoRepository.getAllByUserDetails(userEntity);
		
		for(TodoEntity todo : todos) {
			returnValue.add(modelMapper.map(todo, TodoDto.class));
		}
		
		
		return returnValue;
	}

	@Override
	public TodoDto getTodoByTodoId(String username, String todoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoEntity deleteTodoOfUser(String username, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoEntity updateTodo(TodoEntity todo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoEntity createTodo(TodoEntity todo) {
		// TODO Auto-generated method stub
		return null;
	}

}
