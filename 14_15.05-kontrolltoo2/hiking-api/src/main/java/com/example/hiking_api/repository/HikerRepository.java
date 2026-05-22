package com.example.hiking_api.repository;

import com.example.hiking_api.model.Hiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikerRepository extends JpaRepository<Hiker, Long> {
}