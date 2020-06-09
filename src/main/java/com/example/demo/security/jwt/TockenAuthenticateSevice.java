package com.example.demo.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TockenAuthenticateSevice {
  private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 7; // 7 days
  private String secret = "TheSecretOfMrChen";
  private String tokenPrefix = "Bearer";
  private String headerString = "Authorization";

  public String addAuthentication(String username) {
    // We are generate a token now.
    String JWT = Jwts.builder().setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).signWith(SignatureAlgorithm.HS512, secret)
        .compact();
    return tokenPrefix + " " + JWT;
  }

  public Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(headerString);
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7, token.length());
      // parse the token.
      String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
      if (username != null) // we managed to retrieve a user
      {
        return new AuthenticateUser(username);
      }
    }
    return null;
  }
}