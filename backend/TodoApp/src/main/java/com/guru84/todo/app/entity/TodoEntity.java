package com.guru84.todo.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="todos")
public class TodoEntity implements Serializable {

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String todoId;

	@Column(length = 30, nullable = false)
	private String description;
	
	@Column(length = 30, nullable = false)
	private Date dueDate;
	
	@Column(length = 15, nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", dueDate=" + dueDate + ", status=" + status + "]";
	}
	
}
