package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.post.PostRequest;
import com.donato.jsonplaceholder.model.post.PostResponse;
import com.donato.jsonplaceholder.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    public ResponseEntity<List<PostResponse>> listAll(){
        List<PostResponse> response = service.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id){
        PostResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest request){
        PostResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@RequestBody PostRequest request, @PathVariable Long id){
        PostResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }
}
