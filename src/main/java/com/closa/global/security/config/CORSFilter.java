package com.closa.global.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Configuration
public class CORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, javax.servlet.FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000, http://localhost:3000 ");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "origin, authorization, content-type, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
       // if ("OPTIONS".equals(request.getMethod())) {
       //     response.setStatus(HttpServletResponse.SC_OK);
       // } else {
            filterChain.doFilter(request, response);
      //  }
    }
}