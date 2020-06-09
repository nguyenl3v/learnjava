package com.example.demo.service.user;

import com.example.demo.model.registerModel;

public interface Iuser {
  registerModel findByEmail(String email);
}