package com.urlshortener.shortened.controlleradvices;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(EmptyResultDataAccessException dx){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This data isn't stored");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDatabaseDuplicateKey(DuplicateKeyException dx){
        return ResponseEntity.status(HttpStatus.FOUND).body("This url has alreadby been saved");
    }
}
