package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.registerModel;
import com.example.demo.repository.registerRepository;
import com.example.demo.utils.status.ResponMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class registerController {
  private HashMap<String, Object> results; 

  @Autowired
  private final registerRepository registerRepository;

  registerController(registerRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  @PostMapping("/api/register")
  public @ResponseBody ResponseEntity<?> Create(@RequestBody registerModel model, HttpServletRequest request,
      HttpServletResponse response) {
    results = new HashMap<>();
    if (model.getU_name() == null || model.getEmail() == null || model.getPassword() == null) {
      return ResponMethod.SendFailResponse(request, response, results, "all field is required", 404);
    } else {
      BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
      model.setU_name(model.getU_name());
      model.setEmail(model.getEmail());
      model.setPassword(bcryptPasswordEncoder.encode(model.getPassword()));
      return ResponMethod.SendSuccessResponse(request, response, results, registerRepository.save(model));
    }
  }

  @GetMapping("/api/user")
  public @ResponseBody ResponseEntity<?> GetUser(HttpServletRequest request, HttpServletResponse response) {
    results = new HashMap<>();
    List<registerModel> rgt;
    rgt = registerRepository.findAll();
    return ResponMethod.SendSuccessResponseWithCount(request, response, results, rgt, rgt.size());
  }

  @PostMapping("/api/login")
  public @ResponseBody 
  ResponseEntity<?> Login(@RequestBody registerModel model, HttpServletResponse response, HttpServletRequest request){
    results = new HashMap<>();
    Map<Object,Object> md = new HashMap<>();
    md.put("user",registerRepository.findByEmail(model.getEmail()));
    if( registerRepository.findByEmail(model.getEmail()) != null){
      return ResponMethod.SendSuccessResponse(request, response, results, md);
    }else{
      return ResponMethod.SendFailResponse(request, response, results, "email not found", 404);
    }
  }
}