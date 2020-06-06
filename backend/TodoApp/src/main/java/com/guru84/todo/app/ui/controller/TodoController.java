package com.guru84.todo.app.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.guru84.todo.app.service.TodoService;
import com.guru84.todo.app.shared.dto.TodoDto;
import com.guru84.todo.app.ui.model.response.TodoRest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")  //http://localhost:8080/guru84-tasks/users
public class TodoController {
	
	
	@Autowired
	TodoService todoService;
		

	@GetMapping(path = "/{userId}/todos",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public List<TodoRest> getTodos(@PathVariable String userId){

		List<TodoRest> returnValue = new ArrayList<>();
		
		 List<TodoDto> todoDto = todoService.getTodoByUserId(userId);
		 
		if (todoDto != null && !todoDto.isEmpty()) { 
			ModelMapper modelMapper = new ModelMapper();

			java.lang.reflect.Type listType = new TypeToken<List<TodoRest>>() {}.getType();
			returnValue = modelMapper.map(todoDto, listType);
		}
		return returnValue;
	}
	
	@GetMapping(path = "/{userId}/todos/{todoId}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public TodoRest getUserTodosByTodoId(@PathVariable String userId, @PathVariable long todoId)
	{
		TodoRest returnValue = new TodoRest();
		
		TodoDto todoDto = todoService.getTodoByTodoId(userId,todoId);
		 
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(todoDto, TodoRest.class);

		return returnValue;
	}
	
	@PutMapping(path = "/{username}/todos/{id}",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TodoRest> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody TodoDto todo){
		TodoRest returnValue = new TodoRest();
		TodoDto todoUpdated = todoService.updateTodo(todo);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(todoUpdated, TodoRest.class);
		 return new ResponseEntity<TodoRest>(returnValue, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		TodoDto todo = todoService.deleteTodoOfUser(username,id);
		 if(todo != null)
			 return ResponseEntity.noContent().build();
		 return ResponseEntity.notFound().build();
	}
	
	//create a new Todo
	@PostMapping(path = "/{username}/todos", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> crateTodo(@PathVariable String username, @RequestBody TodoDto todo){
		TodoDto createdTodo = todoService.createTodo(todo);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		 .path("/{id}").buildAndExpand(createdTodo.getTodoId()).toUri();
		 return ResponseEntity.created(uri).build();
	}

}
