package com.donato.jsonplaceholder.repository.jpa;

import com.donato.jsonplaceholder.model.todo.TodoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoDomain, Long> {
}
