package com.priscripto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmailException(DuplicateEmailException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
       // response.put("status", HttpStatus.CONFLICT.toString()); // 409 Conflict
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DuplicateTypeNameException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateSpecializationNameException(DuplicateTypeNameException ex) {
    	 Map<String, String> response = new HashMap<>();
         response.put("error", ex.getMessage());
         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException ex) {
    	 Map<String, String> response = new HashMap<>();
         response.put("error", ex.getMessage());
    	return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    

    @ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidSpecialization(InvalidTypeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleIdNotFoundException(IdNotFoundException ex)
    {
        Map<String,String> response =new HashMap<>();
        response.put("error",ex.getMessage());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


}
