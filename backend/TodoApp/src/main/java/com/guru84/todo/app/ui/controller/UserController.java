package com.guru84.todo.app.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.guru84.todo.app.exceptions.UserServiceException;
import com.guru84.todo.app.service.TodoService;
import com.guru84.todo.app.service.UserService;
import com.guru84.todo.app.shared.dto.TodoDto;
import com.guru84.todo.app.shared.dto.UserDto;
import com.guru84.todo.app.ui.model.request.UserDetailsRequestModel;
import com.guru84.todo.app.ui.model.response.ErrorMessages;
import com.guru84.todo.app.ui.model.response.OperationStatusModel;
import com.guru84.todo.app.ui.model.response.RequestOperationName;
import com.guru84.todo.app.ui.model.response.RequestOperationStatus;
import com.guru84.todo.app.ui.model.response.TodoRest;
import com.guru84.todo.app.ui.model.response.UserRest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;




@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TodoService todoService;
	
	@GetMapping(path = "/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id)
	{
		UserRest returnValue = new UserRest();
		
		UserDto dto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(dto, returnValue);
		
		return returnValue;
	}
	
	@PostMapping( consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		UserRest returnValue = new UserRest();
		
		if (userDetails.getFirstName().isEmpty()) {
		//	throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		//UserDto userDto = new UserDto();
		//BeanUtils.copyProperties(userDetails, userDto);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		//BeanUtils.copyProperties(createdUser, returnValue);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		return returnValue;
	}
	
	@PutMapping( path = "/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUsr = userService.updateUser(id,userDto);
		BeanUtils.copyProperties(updatedUsr, returnValue);
		
		
		return returnValue;
		
	}
	
	@DeleteMapping(path = "/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String id)
	{
		OperationStatusModel operationStatus = new OperationStatusModel();
		operationStatus.setOperationName(RequestOperationName.DELETE.name());
		if (userService.deleteUserByUserId(id))
			operationStatus.setOperationResult(RequestOperationStatus.SUCESS.name());
		else 
			operationStatus.setOperationResult(RequestOperationStatus.ERROR.name());
	    return operationStatus;
	}
	
	@GetMapping
	public List<UserRest> getUsers(@RequestParam(value="page",defaultValue="1")int page,
			@RequestParam(value="limit",defaultValue="1")int limit){
		List<UserRest> usersValue = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page,limit);
		
		for(UserDto user : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(user, userModel);
			usersValue.add(userModel);
			}
		
		return usersValue;
	}
	
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
	
	@PutMapping(path = "users/{username}/todos/{id}",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TodoRest> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody TodoDto todo){
		TodoRest returnValue = new TodoRest();
		TodoDto todoUpdated = todoService.updateTodo(todo);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(todoUpdated, TodoRest.class);
		 return new ResponseEntity<TodoRest>(returnValue, HttpStatus.OK);
	}
	
	//create a new Todo
	@PostMapping(path = "users/{username}/todos", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> crateTodo(@PathVariable String username, @RequestBody TodoDto todo){
		TodoDto createdTodo = todoService.createTodo(todo);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		 .path("/{id}").buildAndExpand(createdTodo.getTodoId()).toUri();
		 return ResponseEntity.created(uri).build();
	}

}
