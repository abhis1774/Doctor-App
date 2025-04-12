package com.priscripto.util;

import com.priscripto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenExtractor {

    private  final JwtUtil jwtUtil;

    @Autowired
    public  JwtTokenExtractor(JwtUtil jwtUtil)
    {
        this.jwtUtil =jwtUtil;
    }

// It will extract the Id from the Jwt tokens.
    public  Long extractUserIdFromToken(String token)
    {
        if(token!=null && jwtUtil.validateToken(token)) {
            return jwtUtil.extractUserId(token);
        }
        return null;
    }

}
