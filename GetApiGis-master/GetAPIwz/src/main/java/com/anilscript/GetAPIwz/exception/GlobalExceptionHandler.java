package com.anilscript.GetAPIwz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Resource Not Found (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        logger.warn("Resource not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Invalid Input (400)
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInput(InvalidInputException ex, WebRequest request) {
        logger.warn("Invalid input: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    // Handle BadCredentialsException (invalid username/password)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        logger.error("Authentication failed: {}", ex.getMessage());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", "Invalid username or password");
        body.put("path", "/login"); // optional, adjust if needed

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    // Generic Exception (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unexpected error occurred", ex);
        return buildErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // Build standard error response
    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", OffsetDateTime.now(ZoneOffset.UTC).toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", extractPath(request));
        return new ResponseEntity<>(body, status);
    }

    // Helper to clean up path string
    private String extractPath(WebRequest request) {
        String desc = request.getDescription(false); // e.g., "uri=/offices/circle"
        return desc.startsWith("uri=") ? desc.substring(4) : desc;
    }
}

//package com.anilscript.GetAPIwz.exception;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    // Resource Not Found (404)
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
//        logger.warn("Resource not found: {}", ex.getMessage());
//        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getDescription(false));
//    }
//
//    // Invalid Input (400)
//    @ExceptionHandler(InvalidInputException.class)
//    public ResponseEntity<Object> handleInvalidInput(InvalidInputException ex, WebRequest request) {
//        logger.warn("Invalid input: {}", ex.getMessage());
//        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getDescription(false));
//    }
//
//    // Internal Server Error (500)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
//        logger.error("Unexpected error occurred", ex);
//        return buildErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false));
//    }
//
//    // Common error response structure
//    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, String path) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", OffsetDateTime.now(ZoneOffset.UTC));
//        body.put("status", status.value());
//        body.put("error", status.getReasonPhrase());
//        body.put("message", message);
//        body.put("path", path);
//        return new ResponseEntity<>(body, status);
//    }
//}
