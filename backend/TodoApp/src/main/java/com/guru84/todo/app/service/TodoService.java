package com.guru84.todo.app.service;

import java.util.List;

import com.guru84.todo.app.entity.TodoEntity;
import com.guru84.todo.app.shared.dto.TodoDto;

public interface TodoService {
	
	List<TodoDto> getTodoByUserId(String userId);
	
	TodoDto getTodoByTodoId(String username, String id);
	
	TodoEntity deleteTodoOfUser(String username, long id);

	TodoEntity updateTodo(TodoEntity todo);

	TodoEntity createTodo(TodoEntity todo);

}
