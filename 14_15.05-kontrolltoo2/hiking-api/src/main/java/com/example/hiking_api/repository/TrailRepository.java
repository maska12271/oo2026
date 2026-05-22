package com.example.hiking_api.repository;

import com.example.hiking_api.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailRepository extends JpaRepository<Trail, Long> {
}