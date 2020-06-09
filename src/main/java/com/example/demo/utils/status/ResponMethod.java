package com.example.demo.utils.status;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponMethod {
  public static ResponseEntity<?> SendSuccessResponse(HttpServletRequest request, HttpServletResponse response,
      HashMap<String, Object> results, Object object) {
    results.put("success", "true");
    results.put("data", object);
    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  public static ResponseEntity<?> SendSuccessResponseWithCount(HttpServletRequest request, HttpServletResponse response,
      HashMap<String, Object> results, Object object, Integer count) {
    results.put("success", "true");
    results.put("count", count);
    results.put("data", object);
    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  public static ResponseEntity<?> SendFailResponse(HttpServletRequest request, HttpServletResponse response,
      HashMap<String, Object> results, String message, Integer status) {
    results.put("message", message);
    results.put("success", "false");
    switch (status) {
      case 400:
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(results);
      case 404:
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
      default:
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(results);
    }
  }

  public static ResponseEntity<?> SendMessageResponse(HttpServletRequest request, HttpServletResponse response,
      HashMap<String, Object> results, String message) {
    results.put("message", message);
    results.put("success", "true");
    return new ResponseEntity<>(results, HttpStatus.OK);
  }

}