package com.learn.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.api.dto.ErrorResponse;
import com.learn.api.dto.ResponseWrapper;

import jakarta.security.enterprise.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseBody
        public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error) -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                });

                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                "Validation failed",
                                errors);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(AuthenticationException.class)
        @ResponseBody
        public ResponseEntity<ResponseWrapper<String>> handleAuthenticationException(AuthenticationException ex) {
                ex.printStackTrace(); // Log the exception details
                ResponseWrapper<String> response = new ResponseWrapper<>(
                                HttpStatus.UNAUTHORIZED.value(),
                                "Invalid email or password",
                                null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        @ExceptionHandler(Exception.class)
        @ResponseBody
        public ResponseEntity<ErrorResponse> handleException(Exception ex) {
                ex.printStackTrace(); // Log the exception details
                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "An unexpected error occurred",
                                Map.of("error", "Internal Server Error"));
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(UnauthorizedAccessException.class)
        @ResponseBody
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
