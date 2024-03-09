package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.todo.TodoRequest;
import com.donato.jsonplaceholder.model.todo.TodoResponse;
import com.donato.jsonplaceholder.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping
    public ResponseEntity<List<TodoResponse>> listAll(){
        List<TodoResponse> response = service.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<TodoResponse> getById(@PathVariable Long id){
        TodoResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        TodoResponse response = service.create(request);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@RequestBody TodoRequest request, @PathVariable Long id){
        TodoResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }

}
