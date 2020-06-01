package com.guru84.todo.app.controller;

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

import com.guru84.todo.app.model.Todo;
import com.guru84.todo.app.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping(path = "users/{username}/todos")
	public List<Todo> getTodos(@PathVariable String username){
		return todoService.getTodoOfUser(username);
	}
	
	@DeleteMapping(path = "users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		 Todo todo = todoService.deleteTodoOfUser(username,id);
		 if(todo != null)
			 return ResponseEntity.noContent().build();
		 return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path = "users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id){
		return todoService.findById(username, id);
	}
	
	@PutMapping(path = "users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		 Todo todoUpdated = todoService.updateTodo(todo);
		 return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);
	}
	//create a new Todo
	//POST -> users/{user_name}/todos
	@PostMapping(path = "users/{username}/todos")
	public ResponseEntity<Void> crateTodo(@PathVariable String username, @RequestBody Todo todo){
		 Todo createdTodo = todoService.createTodo(todo);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		 .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	}

}
