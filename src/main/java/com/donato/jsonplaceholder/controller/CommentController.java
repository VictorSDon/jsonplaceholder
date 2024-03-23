package com.donato.jsonplaceholder.controller;


import com.donato.jsonplaceholder.model.comment.CommentRequest;
import com.donato.jsonplaceholder.model.comment.CommentResponse;
import com.donato.jsonplaceholder.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService service;

    @GetMapping

    public ResponseEntity<List<CommentResponse>> listAll(){
        List<CommentResponse> response = service.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")

    public ResponseEntity<CommentResponse> getById(@PathVariable Long id){
        CommentResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping

    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request){
        CommentResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")

    public ResponseEntity<CommentResponse> update(@RequestBody CommentRequest request, @PathVariable Long id){
        CommentResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }
}
