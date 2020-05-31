package com.guru84.todo.app.service;

import java.util.List;

import com.guru84.todo.app.model.Todo;

public interface TodoService {
	
	List<Todo> getTodoOfUser(String username);

}
