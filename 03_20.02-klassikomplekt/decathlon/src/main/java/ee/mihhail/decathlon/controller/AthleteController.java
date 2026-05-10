package ee.mihhail.decathlon.controller;

import ee.mihhail.decathlon.entity.Athlete;
import ee.mihhail.decathlon.entity.Result;
import ee.mihhail.decathlon.repository.AthleteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("athletes")
    public List<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    @PostMapping("athletes")
    public List<Athlete> addAthlete(@Valid @RequestBody Athlete athlete) {
        for (Result result : athlete.getResults()) {
            result.setAthlete(athlete);
            result.setPoints(calculatePoints(result.getSportEvent(), result.getResultValue()));
        }

        athleteRepository.save(athlete);
        return athleteRepository.findAll();
    }

    @GetMapping("athletes/{id}/points")
    public Map<String, Object> getTotalPoints(@PathVariable Long id) {
        Athlete athlete = athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlete not found"));

        int totalPoints = athlete.getResults()
                .stream()
                .mapToInt(Result::getPoints)
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("athleteId", athlete.getId());
        response.put("name", athlete.getName());
        response.put("totalPoints", totalPoints);

        return response;
    }

    private int calculatePoints(String sportEvent, double resultValue) {
        if (sportEvent.equals("100m")) {
            return (int) Math.round(25.4347 * Math.pow(18 - resultValue, 1.81));
        }

        if (sportEvent.equals("kaugushüpe")) {
            return (int) Math.round(0.14354 * Math.pow(resultValue * 100 - 220, 1.4));
        }

        throw new RuntimeException("Unknown sport event");
    }
}