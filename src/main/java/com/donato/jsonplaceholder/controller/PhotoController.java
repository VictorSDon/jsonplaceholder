package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.album.AlbumRequest;
import com.donato.jsonplaceholder.model.photo.PhotoRequest;
import com.donato.jsonplaceholder.model.photo.PhotoResponse;
import com.donato.jsonplaceholder.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService service;

    @GetMapping
    public ResponseEntity<List<PhotoResponse>> listall(){
        List<PhotoResponse> response = service.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponse> getById(@PathVariable Long id){
        PhotoResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping

    public ResponseEntity<PhotoResponse> create(@RequestBody PhotoRequest request){
        PhotoResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoResponse> update(@RequestBody PhotoRequest request,@PathVariable Long id){
        PhotoResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }
}
