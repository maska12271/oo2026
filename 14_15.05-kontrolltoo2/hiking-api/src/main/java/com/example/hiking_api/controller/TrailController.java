package com.example.hiking_api.controller;

import com.example.hiking_api.model.Hiker;
import com.example.hiking_api.model.Trail;
import com.example.hiking_api.repository.HikerRepository;
import com.example.hiking_api.repository.TrailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trails")
public class TrailController {

    private final TrailRepository trailRepository;
    private final HikerRepository hikerRepository;

    public TrailController(TrailRepository trailRepository, HikerRepository hikerRepository) {
        this.trailRepository = trailRepository;
        this.hikerRepository = hikerRepository;
    }

    @PostMapping
    public Trail addTrail(@RequestBody Trail trail) {
        return trailRepository.save(trail);
    }

    @GetMapping
    public List<Trail> getAllTrails() {
        return trailRepository.findAll();
    }

    @PutMapping("/{trailId}/hikers/{hikerId}")
    public ResponseEntity<Trail> addHikerToTrail(@PathVariable Long trailId, @PathVariable Long hikerId) {
        Optional<Trail> optionalTrail = trailRepository.findById(trailId);
        Optional<Hiker> optionalHiker = hikerRepository.findById(hikerId);

        if (optionalTrail.isEmpty() || optionalHiker.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trail trail = optionalTrail.get();
        Hiker hiker = optionalHiker.get();

        trail.getHikers().add(hiker);
        trailRepository.save(trail);

        return ResponseEntity.ok(trail);
    }

    @GetMapping("/{trailId}/hiker-count")
    public ResponseEntity<Integer> getHikerCount(@PathVariable Long trailId) {
        Optional<Trail> optionalTrail = trailRepository.findById(trailId);

        if (optionalTrail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        int count = optionalTrail.get().getHikers().size();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{trailId}/total-hiker-kilometers")
    public ResponseEntity<Double> getTotalHikerKilometers(@PathVariable Long trailId) {
        Optional<Trail> optionalTrail = trailRepository.findById(trailId);

        if (optionalTrail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        double total = optionalTrail.get()
                .getHikers()
                .stream()
                .mapToDouble(Hiker::getTotalKilometers)
                .sum();

        return ResponseEntity.ok(total);
    }
}