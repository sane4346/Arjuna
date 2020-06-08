package com.guru84.todo.app.ui.model.response;

public class TodoRest {
	
	private long todoId;
	private String description;
	private String dueDate;
	private String status;
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
	public Boolean getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

}
