package com.example.demo.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticatedFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = new TockenAuthenticateSevice().getAuthentication((HttpServletRequest)servletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS, PUT, DELETE");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers","Accept, Content-Type, Origin, Content-Length, Accept-Encoding, X-CSRF-Token, Authorization, X-Requested-With");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Max-Age","3600");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Credentials","true");
//        ((HttpServletResponse) servletResponse).setContentType("application/json");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // For HTTP OPTIONS
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        filterChain.doFilter(request,servletResponse);
    }
}
