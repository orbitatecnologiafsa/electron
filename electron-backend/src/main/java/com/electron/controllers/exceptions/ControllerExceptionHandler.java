package com.electron.controllers.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotFoundException e, HttpServletRequest request) {
        String error = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<StandardError> resourceAlreadyExists(AlreadyExistException e, HttpServletRequest request) {
        String error = "Recurso já existe";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        String error = "Argumento inválido";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        String error = "Violação de integridade de dados";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Erro de validação";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        
        List<String> errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getField() + ": " + x.getDefaultMessage())
            .collect(Collectors.toList());
            
        StandardError err = new StandardError(
            Instant.now(), 
            status.value(), 
            error, 
            String.join("; ", errors), 
            request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> genericException(Exception e, HttpServletRequest request) {
        String error = "Erro interno do servidor";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}