package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.album.AlbumRequest;
import com.donato.jsonplaceholder.model.album.AlbumResponse;
import com.donato.jsonplaceholder.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService service;
    @GetMapping

    public ResponseEntity<List<AlbumResponse>> listAll(){
        List<AlbumResponse> response =service.listAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")

    public ResponseEntity<AlbumResponse> getById(@PathVariable Long id){
        AlbumResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping

    public ResponseEntity<AlbumResponse> create(@RequestBody AlbumRequest request){
        AlbumResponse response =service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")

    public ResponseEntity<AlbumResponse> update(@RequestBody AlbumRequest request, @PathVariable Long id){
        AlbumResponse response =service.update(request, id);
        return ResponseEntity.ok(response);
    }
}
