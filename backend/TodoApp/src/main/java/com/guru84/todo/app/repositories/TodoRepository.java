package com.guru84.todo.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru84.todo.app.entity.TodoEntity;
import com.guru84.todo.app.entity.UserEntity;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
	List<TodoEntity> getAllByUserDetails(UserEntity userEntity);
	TodoEntity findByTodoId(String todoId);
}
