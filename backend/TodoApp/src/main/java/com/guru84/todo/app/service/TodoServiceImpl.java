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
	}

	@Override
	public List<Todo> getTodoOfUser(String username) {
		// TODO Auto-generated method stub
		return todos;
	}

}
