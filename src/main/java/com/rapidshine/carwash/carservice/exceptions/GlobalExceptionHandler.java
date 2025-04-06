package com.rapidshine.carwash.carservice.exceptions;

import com.rapidshine.carwash.carservice.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> customerNotFoundException(CustomerNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage(), LocalDateTime.now()
                ,HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ErrorResponse> invalidJwtTokenException(InvalidJwtTokenException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage(),LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()));

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralExcpeiton(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(),
                LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR    .value()));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeExcpeiton(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(),
                LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR    .value()));
    }
    @ExceptionHandler(CarAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleCarAlreadyExistsException(CarAlreadyExists e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage(),
                LocalDateTime.now(),HttpStatus.CONFLICT.value()));
    }

}
