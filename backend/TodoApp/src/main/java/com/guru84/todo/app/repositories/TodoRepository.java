package com.guru84.todo.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru84.todo.app.entity.TodoEntity;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
	List<TodoEntity> getAllByUsername(String username);
	List<TodoEntity> findByTodoId(long todoId);
	List<TodoEntity> getAllByUsernameAndIsArchived(String username, Boolean isArchived);
	List<TodoEntity> findByTodoIdAndIsArchived(long todoId, Boolean isArchived);
}
