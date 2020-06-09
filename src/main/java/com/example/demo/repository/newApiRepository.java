package com.example.demo.repository;

import com.example.demo.model.newApiModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface newApiRepository extends JpaRepository<newApiModel, Long> {
    
}