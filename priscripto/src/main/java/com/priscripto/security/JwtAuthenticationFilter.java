package com.priscripto.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
       
        
             if( 
            		    requestURI.startsWith("/api/v1/doctors/") ||
            	        requestURI.startsWith("/api/v1/doctor/")  ||
            	        requestURI.startsWith("/api/v1/patient/") ||
            	        requestURI.startsWith("/api/auth/login")  ||
            	        requestURI.startsWith("/api/auth/logout") ||
                        requestURI.startsWith("/api/v1/doctors")

            	        
               )
        		 {
            	 System.out.println(requestURI);
            chain.doFilter(request, response);
            return;
        }

        String token = extractTokenFromCookies(request);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Missing JWT token. Please log in.\"}");
            return;
        }

        try {
//            if (!jwtUtil.validateToken(token)) {
//                throw new MalformedJwtException("Invalid JWT token.");
//            }

//            if (userServiceImpl.isTokenInvalid(token)) {
//                throw new ExpiredJwtException(null, null, "JWT token is expired.");
//            }

            Claims claims = jwtUtil.extractAllClaims(token);
            SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(claims.get("role", String.class)));


        } catch (Exception  e) {
        
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"JWT token has expired. Please log in again.\"}"+e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }

    private String extractTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
