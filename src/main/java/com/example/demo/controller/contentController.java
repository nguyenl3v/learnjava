package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.contentModel;
import com.example.demo.repository.contentRepository;
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

@RestController
public class contentController {

  private HashMap<String, Object> results;
  @Autowired
  private final contentRepository ctModel;

  contentController(contentRepository ctModel) {
    this.ctModel = ctModel;
  }

  @GetMapping("/api/content")
  public @ResponseBody ResponseEntity<?> GetAll(HttpServletRequest request, HttpServletResponse response) {
    results = new HashMap<>();
    List<contentModel> contentList;
    contentList = ctModel.findAll();
    if (contentList != null) {
      return ResponMethod.SendSuccessResponseWithCount(request, response, results, contentList, contentList.size());
    } else {
      return ResponMethod.SendSuccessResponse(request, response, results, null);
    }
  }

  @PostMapping("/api/content/add")
  public @ResponseBody 
  ResponseEntity<?> CreateContent(@RequestBody contentModel ct, HttpServletRequest request,
      HttpServletResponse response) {
    results = new HashMap<>();
    ctModel.save(ct);
    return ResponMethod.SendSuccessResponse(request, response, results, ct);
  }

  @PutMapping("/api/content/{id}")
  public @ResponseBody
  ResponseEntity<?> Edit(@PathVariable Integer id, @RequestBody contentModel ct,HttpServletRequest request,HttpServletResponse response) {
    results = new HashMap<>();
    contentModel ctmd = ctModel.getOne(id);
   
    if(ct.getContent() != null) {
       ctmd.setContent(ct.getContent());
    }
    return ResponMethod.SendSuccessResponse(request, response, results,ctModel.save(ctmd));
  }

  @DeleteMapping("/api/content/{id}")
  public @ResponseBody
  ResponseEntity<?> Delete(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response){
    results = new HashMap<>();
    if(!ctModel.existsById(id)){
      return ResponMethod.SendFailResponse(request, response, results, "id is not empty", 404);
    }else{
      ctModel.deleteById(id);
      return ResponMethod.SendSuccessResponse(request, response, results, null);
    }
  }
}