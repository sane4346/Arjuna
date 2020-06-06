package com.guru84.todo.app.ui.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guru84.todo.app.entity.TodoEntity;
import com.guru84.todo.app.service.TodoService;
import com.guru84.todo.app.shared.dto.TodoDto;

@RestController
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
//	@GetMapping(path = "users/{username}/todos")
//	public List<TodoEntity> getTodos(@PathVariable String username){
//		return todoService.getTodoOfUser(username);
//	}
//	
//	@DeleteMapping(path = "users/{username}/todos/{id}")
//	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
//		 TodoEntity todo = todoService.deleteTodoOfUser(username,id);
//		 if(todo != null)
//			 return ResponseEntity.noContent().build();
//		 return ResponseEntity.notFound().build();
//	}
	
//	@GetMapping(path = "users/{username}/todos/{id}")
//	public TodoDto getTodo(@PathVariable String username, @PathVariable long id){
//		return todoService.findById(username, id);
//	}
	


}
