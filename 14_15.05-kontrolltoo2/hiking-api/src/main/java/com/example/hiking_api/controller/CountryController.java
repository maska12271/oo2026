package com.example.hiking_api.controller;

import com.example.hiking_api.model.Country;
import com.example.hiking_api.model.Hiker;
import com.example.hiking_api.model.Trail;
import com.example.hiking_api.repository.CountryRepository;
import com.example.hiking_api.repository.TrailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryRepository countryRepository;
    private final TrailRepository trailRepository;

    public CountryController(CountryRepository countryRepository, TrailRepository trailRepository) {
        this.countryRepository = countryRepository;
        this.trailRepository = trailRepository;
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @PutMapping("/{countryId}/trails/{trailId}")
    public ResponseEntity<Trail> addTrailToCountry(@PathVariable Long countryId, @PathVariable Long trailId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        Optional<Trail> optionalTrail = trailRepository.findById(trailId);

        if (optionalCountry.isEmpty() || optionalTrail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Country country = optionalCountry.get();
        Trail trail = optionalTrail.get();

        trail.setCountry(country);
        trailRepository.save(trail);

        return ResponseEntity.ok(trail);
    }

    @GetMapping("/{countryId}/hiker-count")
    public ResponseEntity<Integer> getCountryHikerCount(@PathVariable Long countryId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);

        if (optionalCountry.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Country country = optionalCountry.get();
        Set<Hiker> uniqueHikers = new HashSet<>();

        for (Trail trail : country.getTrails()) {
            uniqueHikers.addAll(trail.getHikers());
        }

        return ResponseEntity.ok(uniqueHikers.size());
    }

    @GetMapping("/{countryId}/top-hiker")
    public ResponseEntity<Hiker> getTopHikerInCountry(@PathVariable Long countryId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);

        if (optionalCountry.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Country country = optionalCountry.get();
        Set<Hiker> uniqueHikers = new HashSet<>();

        for (Trail trail : country.getTrails()) {
            uniqueHikers.addAll(trail.getHikers());
        }

        if (uniqueHikers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Hiker topHiker = uniqueHikers.stream()
                .max((h1, h2) -> Double.compare(h1.getTotalKilometers(), h2.getTotalKilometers()))
                .orElse(null);

        return ResponseEntity.ok(topHiker);
    }
}