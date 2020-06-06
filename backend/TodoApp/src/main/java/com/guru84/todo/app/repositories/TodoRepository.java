package com.guru84.todo.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru84.todo.app.entity.TodoEntity;
import com.guru84.todo.app.entity.User;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
	List<TodoEntity> getAllByUserDetails(User userEntity);
	List<TodoEntity> findByTodoId(long todoId);
}
