package com.guru84.todo.app.shared.dto;

import java.util.Date;

public class TodoDto {
	
	private long todoId;
	private String description;
	private Date dueDate;
	private String status;
	public Boolean getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}
	private String  username;
	private Boolean isArchived;
	

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
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
