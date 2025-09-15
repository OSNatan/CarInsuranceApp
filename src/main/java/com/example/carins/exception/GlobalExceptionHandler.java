package com.example.carins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCarNotFoundException(CarNotFoundException ex){
        Map<String,Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", 404);
        body.put("error", "Car not found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePolicyNotFoundException(PolicyNotFoundException ex){
        Map<String,Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", 404);
        body.put("error", "Policy not found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidDateException(InvalidDateException ex){
        Map<String,Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", 400);
        body.put("error", "Invalid date");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex){
        Map<String,Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", 500);
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
