package com.electroapp.electro_app.infrastructure.controllers;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electroapp.electro_app.application.services.IRegionService;
import com.electroapp.electro_app.domain.entities.Region;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @GetMapping
    public List<Region> list() {
        return regionService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            return ResponseEntity.ok(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Region region, BindingResult result) {
        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.save(region));
    }
}