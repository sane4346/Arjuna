package com.guru84.todo.app.service;

import java.util.List;

import com.guru84.todo.app.shared.dto.TodoDto;

public interface TodoService {
	
	List<TodoDto> getTodoByUserId(String userId);
	
	TodoDto getTodoByTodoId(String username, long todoId);
	
	TodoDto deleteTodoOfUser(String username, long todoId);

	TodoDto updateTodo(TodoDto todo);

	TodoDto createTodo(TodoDto todo);
	
	TodoDto archiveTodo(String username, long todoId);
	
	List<TodoDto> getArchivedTodosByUserId(String userId);

}
