package com.example.demo.repository;

import com.example.demo.model.contentModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface contentRepository extends JpaRepository<contentModel, Integer> {
  
}