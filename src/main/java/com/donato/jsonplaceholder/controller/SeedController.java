package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seed")
public class SeedController {
    private final SeedService service;
    @PostMapping("/full")
    public ResponseEntity<Void> fullSeed(){
        service.fullSeed();
        return ResponseEntity.noContent().build();
    }
}
