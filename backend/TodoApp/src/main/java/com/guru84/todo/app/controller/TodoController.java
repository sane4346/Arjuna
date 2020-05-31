package com.guru84.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.guru84.todo.app.model.Todo;
import com.guru84.todo.app.service.TodoService;

@RestController
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping(path = "users/{username}/todos")
	public List<Todo> getTodos(@PathVariable String username){
		return todoService.getTodoOfUser(username);
	}

}
