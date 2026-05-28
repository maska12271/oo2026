package com.example.tenderapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String registrationCode;

    @Email
    private String email;

    private String phone;

    private String address;

    private String contactPerson;

    @Column(length = 2000)
    private String notes;

    private Boolean active = true;
}