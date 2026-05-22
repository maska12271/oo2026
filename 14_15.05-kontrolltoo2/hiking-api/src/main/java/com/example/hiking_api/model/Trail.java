package com.example.hiking_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double length;

    @ManyToMany
    @JoinTable(
            name = "hiker_trail",
            joinColumns = @JoinColumn(name = "trail_id"),
            inverseJoinColumns = @JoinColumn(name = "hiker_id")
    )
    private Set<Hiker> hikers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Trail() {
    }

    public Trail(String name, double length) {
        this.name = name;
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public Set<Hiker> getHikers() {
        return hikers;
    }

    public Country getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setHikers(Set<Hiker> hikers) {
        this.hikers = hikers;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}