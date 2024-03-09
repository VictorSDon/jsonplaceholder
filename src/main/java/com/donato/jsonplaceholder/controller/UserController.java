package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.user.request.UserRequest;
import com.donato.jsonplaceholder.model.user.response.UserResponse;
import com.donato.jsonplaceholder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll(){
        List<UserResponse> response = service.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        UserResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request){
        UserResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }
}
