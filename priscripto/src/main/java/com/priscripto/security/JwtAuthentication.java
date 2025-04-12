package com.priscripto.security;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final String role;

    public JwtAuthentication(String role) {
        super(Collections.singletonList(new SimpleGrantedAuthority(role))); // Assign role as authority
        this.role = role;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials required for JWT authentication
    }

    @Override
    public Object getPrincipal() {
        return role; // Return role as the principal identity
    }

    public String getRole() {
        return role; // Custom method to fetch role
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role)); // Ensure type safety
    }
}