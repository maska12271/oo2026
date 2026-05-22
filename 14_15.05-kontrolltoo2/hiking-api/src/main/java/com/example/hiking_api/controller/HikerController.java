package com.example.hiking_api.controller;

import com.example.hiking_api.model.Hiker;
import com.example.hiking_api.repository.HikerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hikers")
public class HikerController {

    private final HikerRepository hikerRepository;

    public HikerController(HikerRepository hikerRepository) {
        this.hikerRepository = hikerRepository;
    }

    @PostMapping
    public Hiker addHiker(@RequestBody Hiker hiker) {
        return hikerRepository.save(hiker);
    }

    @GetMapping
    public List<Hiker> getAllHikers() {
        return hikerRepository.findAll();
    }

    @PutMapping("/{id}/kilometers")
    public ResponseEntity<Hiker> addKilometers(@PathVariable Long id, @RequestParam double amount) {
        Optional<Hiker> optionalHiker = hikerRepository.findById(id);

        if (optionalHiker.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Hiker hiker = optionalHiker.get();
        hiker.setTotalKilometers(hiker.getTotalKilometers() + amount);
        hikerRepository.save(hiker);

        return ResponseEntity.ok(hiker);
    }
}