package com.learn.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.api.dto.ResponseWrapper;

import jakarta.security.enterprise.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<ResponseWrapper<String>> handleAuthenticationException(AuthenticationException ex) {
                ex.printStackTrace(); // Log the exception details
                ResponseWrapper<String> response = new ResponseWrapper<>(
                                HttpStatus.UNAUTHORIZED.value(),
                                "Invalid email or password",
                                null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResponseWrapper<Map<String, String>>> handleValidationExceptions(
                        MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                ResponseWrapper<Map<String, String>> response = new ResponseWrapper<>(
                                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                "Validation failed",
                                errors);

                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ResponseWrapper<String>> handleException(Exception ex) {
                ex.printStackTrace(); // Log the exception details
                ResponseWrapper<String> response = new ResponseWrapper<>(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "An unexpected error occurred",
                                "Internal Server Error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        @ExceptionHandler(UnauthorizedAccessException.class)
        public ResponseEntity<ResponseWrapper<String>> handleUnauthorizedAccessException(
                        UnauthorizedAccessException ex) {
                ex.printStackTrace(); // Log the exception details
                ResponseWrapper<String> response = new ResponseWrapper<>(
                                HttpStatus.FORBIDDEN.value(),
                                "User not authorized",
                                null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
}
