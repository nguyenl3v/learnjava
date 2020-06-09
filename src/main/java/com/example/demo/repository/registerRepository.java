package com.example.demo.repository;

import com.example.demo.model.registerModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface registerRepository extends JpaRepository<registerModel, Integer>{
  registerModel findByEmail(String email);
}