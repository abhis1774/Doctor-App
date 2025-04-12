package com.priscripto.controller;

import com.priscripto.dto.*;
import com.priscripto.model.Role;
import com.priscripto.security.JwtUtil;
import com.priscripto.service.DoctorServiceImpl;
import com.priscripto.service.PatientServiceImpl;
import com.priscripto.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AuthController {
	


	@Autowired
    private  DoctorServiceImpl doctorServiceImpl;
    
    @Autowired
    private PatientServiceImpl patientServiceImpl;
    
    @Autowired
    JwtUtil jwtUtil;
    
//    @Autowired
//    private JwtUtil jwtUtil;

    // login controller for both patient and doctor.
    
    
    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> loginDoctorAndPatient(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        String token = null;
        ResponseCookie jwtCookie = null;
        Map<String, Object> responseBody = new HashMap<>();

        if (loginDTO.getRole() == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Role is required!"));
        }

        LoginDTO user = null;

        switch (loginDTO.getRole()) {
            case DOCTOR:
                token = doctorServiceImpl.loginDoctor(loginDTO);
                user = doctorServiceImpl.getDoctorDetailsByEmail(loginDTO.getEmail()); // Fetch doctor details
                break;
            case PATIENT:
                token = patientServiceImpl.loginPatient(loginDTO);
                user = patientServiceImpl.getPatientDetailsByEmail(loginDTO.getEmail()); // Fetch patient details
                break;
            default:
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Invalid role specified!"));
        }

        jwtCookie = CookieUtil.setCookie(response, "jwt", token, JwtUtil.TOKEN_VALIDITY);
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        responseBody.put("success", true);
        responseBody.put("message", "Login Successful!");
        responseBody.put("jwt", token);
        responseBody.put("user", Map.of(
            "id", user.getId(),
            "firstName",user.getFirstName(),
            "lastName",user.getLastName(),
            "email", user.getEmail(),
            "role", user.getRole()
        ));

        return ResponseEntity.ok(responseBody);
    }

    
    
      // Doctor Controllers
     
    @PostMapping("/v1/doctor/register")
    public ResponseEntity<String> registerDoctor(@RequestBody RegisterDTO registerDTO) {
    	   //System.out.println(registerDTO.getFirstName());
    	 doctorServiceImpl.registerDoctor(registerDTO);
     
        return ResponseEntity.status(HttpStatus.CREATED).body(registerDTO.getFirstName()+" doctor register successFully !");
    }



    
    //Patient Controllers
    
    @PostMapping("/v1/patient/register")

    public ResponseEntity<String> registerPatient(@RequestBody RegisterDTO registerDTO)
    {
     patientServiceImpl.registerPatient(registerDTO);
   
    	return ResponseEntity.status(HttpStatus.CREATED).body(registerDTO.getFirstName()+" patient register !");
    }
    
    

    
    
    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(@CookieValue(name = "jwt", required = false) String token, HttpServletResponse response) {
    	
    	if (token == null || !jwtUtil.validateToken(token)) {
             return ResponseEntity.badRequest().body("Invalid or expired session.");
         }

         // Extract role from JWT and convert it to ENUM
         String roleString = jwtUtil.extractRole(token);
         Role role;
         try {
             role = Role.valueOf(roleString.toUpperCase());
         } catch (IllegalArgumentException e) {
             return ResponseEntity.badRequest().body("Invalid role: " + roleString);
         }

         System.out.println(role);
         // Role-based token invalidation
         switch (role) {
             case DOCTOR:
                 doctorServiceImpl.logout(token);
                 CookieUtil.deleteCookie(response, "jwt");
                 break;
             case PATIENT:
                 patientServiceImpl.logout(token);
                 CookieUtil.deleteCookie(response, "jwt");
                 break;
             default:
                 return ResponseEntity.badRequest().body("Unauthorized access.");
         }

        return ResponseEntity.ok("Logout successful!");
    }
    
}

