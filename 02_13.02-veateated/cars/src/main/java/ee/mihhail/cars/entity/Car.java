package ee.mihhail.cars.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand is missing")
    @Size(min = 2, max = 20, message = "Brand must be 2-20 characters long")
    private String brand;

    @NotBlank(message = "Model is missing")
    @Size(min = 2, max = 30, message = "Model must be 2-30 characters long")
    private String model;

    @Min(value = 1886, message = "Year must be 1886 or newer")
    @Max(value = 2026, message = "Year cannot be in the future")
    private int year;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    @Pattern(regexp = "^[A-Z0-9-]{5,10}$", message = "Registration number format is invalid")
    private String registrationNumber;
}