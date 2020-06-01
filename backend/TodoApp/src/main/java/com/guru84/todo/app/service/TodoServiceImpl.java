package com.guru84.todo.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.guru84.todo.app.model.Todo;
import com.guru84.todo.app.Status;

@Service
public class TodoServiceImpl implements TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static Integer idNum = 0;

	static {
		todos.add(new Todo(++idNum, "new fullstack", new Date(), Status.Start));
		todos.add(new Todo(++idNum, "new java", new Date(), Status.OnGoing));
		todos.add(new Todo(++idNum, "new react", new Date(), Status.Start));
		todos.add(new Todo(++idNum, "new Leanring", new Date(), Status.Completed));
		todos.add(new Todo(++idNum, "new Leanring", new Date(), Status.OnGoing));
	}

	@Override
	public List<Todo> getTodoOfUser(String username) {
		return todos;
	}

	@Override
	public Todo deleteTodoOfUser(String username, long id) {
		Todo todo = findById(username, id);
		if (todo != null) {
			todos.remove(todo);
		}
		return todo;
	}

	@Override
	public Todo findById(String username, long id) {
		for (Todo todo : todos) {
			if (id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}

	@Override
	public Todo updateTodo(Todo todo) {
		return save(todo);
	}

	@Override
	public Todo createTodo(Todo todo) {
		// TODO Auto-generated method stub
		return save(todo);
	}
	
	private Todo save(Todo todo) {
		if (todo.getId() == -1) {
			todo.setId(++idNum);
			todos.add(todo);
		} else {
			deleteTodoOfUser("", todo.getId());
			todos.add(todo);
		}
		return todo;
	}

}
