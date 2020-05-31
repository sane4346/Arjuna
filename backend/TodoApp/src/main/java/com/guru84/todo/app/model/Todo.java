package com.guru84.todo.app.model;

import java.util.Date;

import com.guru84.todo.app.Status;

public class Todo {
	
	private Integer id;
	private String description;
	private Date dueDate;
	private Status status;
	
	public Todo(Integer id, String description, Date dueDate, Status status) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", dueDate=" + dueDate + ", status=" + status + "]";
	}
	
}
