package com.example.demo.service.user;

import com.example.demo.model.registerModel;
import com.example.demo.repository.registerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class user implements Iuser {
  @Autowired
  private registerRepository repository;

  @Override
  public registerModel findByEmail(String email){
    return repository.findByEmail(email);
  }
}