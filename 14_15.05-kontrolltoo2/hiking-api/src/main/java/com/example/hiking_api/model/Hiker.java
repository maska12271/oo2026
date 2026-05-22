package com.example.hiking_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Hiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double totalKilometers;

    @JsonIgnore
    @ManyToMany(mappedBy = "hikers")
    private Set<Trail> trails = new HashSet<>();

    public Hiker() {
    }

    public Hiker(String name, double totalKilometers) {
        this.name = name;
        this.totalKilometers = totalKilometers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotalKilometers() {
        return totalKilometers;
    }

    public Set<Trail> getTrails() {
        return trails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalKilometers(double totalKilometers) {
        this.totalKilometers = totalKilometers;
    }

    public void setTrails(Set<Trail> trails) {
        this.trails = trails;
    }
}