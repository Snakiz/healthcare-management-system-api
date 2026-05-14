package com.shreyas.healthcare.exception;

import com.shreyas.healthcare.dto.respone.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponce<Object>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {

        ApiResponce<Object> responce = new ApiResponce<>(false, ex.getMessage(), null);

        return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponce<Object>> handleGenericException(Exception ex) {

        ApiResponce<Object> responce = new ApiResponce<>(false, ex.getMessage(), null);

        return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
