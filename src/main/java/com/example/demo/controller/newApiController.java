package com.example.demo.controller;

import com.example.demo.model.newApiModel;
import com.example.demo.repository.newApiRepository;
import com.example.demo.utils.status.ResponMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class newApiController {
  private HashMap<String, Object> results;
  @Autowired
  private final newApiRepository repository;

  newApiController(newApiRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/api")
  List<newApiModel> all() {
    return repository.findAll();
  }

  @PostMapping("/api/add")
  newApiModel newApi(@RequestBody newApiModel api) {
    return repository.save(api);
  }

  @GetMapping("/api/{id}")
  newApiModel one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new Error("lam deo gi co id " + id));
  }

  @PutMapping("/api/{id}")
  public @ResponseBody
  ResponseEntity<?> editApi(@RequestBody newApiModel api, @PathVariable Long id,HttpServletRequest request, HttpServletResponse response) {
    results = new HashMap<>();
      if(!repository.existsById(id)){
        return ResponMethod.SendFailResponse(request, response,results,"not fould",404);
      }else{
        newApiModel nw = repository.getOne(id);
        if(api.getTitle() != null){
            nw.setTitle(api.getTitle());
        }
        return ResponMethod.SendSuccessResponse(request, response, results, repository.save(nw));
      }
  }

  @DeleteMapping("/api/{id}")
  void deleteId(@PathVariable Long id){
    repository.deleteById(id);
  }
}