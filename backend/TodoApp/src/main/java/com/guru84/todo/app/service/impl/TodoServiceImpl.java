package com.guru84.todo.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru84.todo.app.entity.TodoEntity;
import com.guru84.todo.app.entity.User;
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

		User userEntity = userRepository.findByUsername(userId);

		if (userEntity == null)
			return returnValue; // Similar to updateUser method , we can use UserServiceException

		ModelMapper modelMapper = new ModelMapper();

		Iterable<TodoEntity> todos = todoRepository.getAllByUsername(userEntity.getUsername());

		for (TodoEntity todo : todos) {
			returnValue.add(modelMapper.map(todo, TodoDto.class));
		}

		return returnValue;
	}

	@Override
	public TodoDto getTodoByTodoId(String userId, long todoId) {

		TodoDto returnValue = new TodoDto();

		User userEntity = userRepository.findByUsername(userId);

		if (userEntity == null)
			return null; // Similar to updateUser method , we can use UserServiceException

		ModelMapper modelMapper = new ModelMapper();

		Iterable<TodoEntity> todos = todoRepository.findByTodoId(todoId);
		for (TodoEntity todo : todos) {
			if (todo.getUsername().equals(userId)) {
				returnValue = modelMapper.map(todo, TodoDto.class);
				return returnValue;
			}
		}

		return null;
	}

	@Override
	public TodoDto deleteTodoOfUser(String username, long todoId) {
		TodoDto returnValue = new TodoDto();

		User userEntity = userRepository.findByUsername(username);

		if (userEntity == null)
			return null; // Similar to updateUser method , we can use UserServiceException

		ModelMapper modelMapper = new ModelMapper();

		Iterable<TodoEntity> todos = todoRepository.findByTodoId(todoId);
		for (TodoEntity todo : todos) {
			if (todo.getUsername().equals(username)) {
				returnValue = modelMapper.map(todo, TodoDto.class);
				todoRepository.delete(todo);
				return returnValue;
			}
		}
		return null;
	}

	@Override
	public TodoDto updateTodo(TodoDto todo) {

		List<TodoEntity> todoEntities = todoRepository.findByTodoId(todo.getTodoId());
		for (TodoEntity todoEntity : todoEntities) {
			if (todoEntity.getUsername().equals(todo.getUsername())) {
				todoEntity.setDescription(todo.getDescription());
				todoEntity.setDueDate(todo.getDueDate());
				todoEntity.setStatus(todo.getStatus());
				todoEntity.setUsername(todo.getUsername());
				todoEntity.setTodoId(todo.getTodoId());
				todoRepository.save(todoEntity);
				return todo;
			}
		}
		return null;
	}

	@Override
	public TodoDto createTodo(TodoDto todo) {

		ModelMapper modelMapper = new ModelMapper();
		Random rand = new Random();
		long id = rand.nextInt((todoRepository.getAllByUsername(todo.getUsername()).size() + 5138));
		todo.setTodoId(id);
		TodoEntity entity = modelMapper.map(todo, TodoEntity.class);
		todoRepository.save(entity);
		return todo;
	}

}
