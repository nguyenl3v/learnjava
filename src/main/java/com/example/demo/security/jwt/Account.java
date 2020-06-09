package com.example.demo.security.jwt;

public class Account {
  private String u_name;
  private String password;

  public void setUsername(String u_name) {
    this.u_name = u_name;
  }

  public String getUsername() {
    return u_name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
}