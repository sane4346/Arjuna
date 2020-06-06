package com.guru84.todo.app.shared.dto;

public class TodoDto {
	
	private long todoId;
	private String description;
	private String dueDate;
	private String status;
	private UserDto userDetails;
	

	public long getTodoId() {
		return todoId;
	}
	public void setTodoId(long todoId) {
		this.todoId = todoId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserDto getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDto userDetails) {
		this.userDetails = userDetails;
	}

}
