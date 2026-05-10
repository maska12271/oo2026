package ee.mihhail.cars.controller;

import ee.mihhail.cars.entity.Car;
import ee.mihhail.cars.repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @PostMapping("cars")
    public List<Car> addCar(@Valid @RequestBody Car car) {
        carRepository.save(car);
        return carRepository.findAll();
    }
}