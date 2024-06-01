package com.example.electronic_wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandlerCard extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CardNotFoundException.class})
    public ResponseEntity<Object> handleException(CardNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
