package com.smartcontactmanager.SmartContactManger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// This annotation indicates that this class will handle exceptions globally
// across all @RestController classes in the application.
@RestControllerAdvice
public class GlobalExceptionHandler {


    // This method handles MethodArgumentNotValidException, which occurs when validation fails.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationexception(MethodArgumentNotValidException ex){
        // BindingResult contains details of validation errors
        BindingResult result = ex.getBindingResult();

        //List to store all th error messsage
        List<String> errors = new ArrayList<>();
        // error.getField() gives the name of the field that failed validation,
        // and error.getDefaultMessage() gives the error message associated with that field.
        for(FieldError error : result.getFieldErrors()){
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }
        // Return a BAD_REQUEST (400) status with the list of validation error messages as the response body
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
   // NullPointerException or DatabaseException
   @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex){
        // Return an INTERNAL_SERVER_ERROR (500) status with a generic error message in case of any unhandled exception
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred:" + ex.getMessage());
    }

}
