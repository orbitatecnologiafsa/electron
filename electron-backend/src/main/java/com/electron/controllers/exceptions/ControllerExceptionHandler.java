package com.electron.controllers.exceptions;

import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> handleValidationErrors(ConstraintViolationException e, HttpServletRequest request) {
        StringBuilder message = new StringBuilder();
        e.getConstraintViolations().forEach(violation ->
            message.append(violation.getMessage()).append("\n")
        );

        StandardError err = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Erro na validação de dados", message.toString(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // Método para tratar exceções de validação de campo
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(this::formatFieldErrorMessage)
                .collect(Collectors.joining(", "));

        String error = "Erro na validação de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                errorMessage,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    private String formatFieldErrorMessage(FieldError fieldError) {
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();

        switch (field) {
            case "email":
                return "Email inválido: " + message;
            case "telefone":
                return "Telefone inválido: " + message;
            case "cpfCnpj":
                return "CPF/CNPJ inválido: " + message;
            case "cep":
                return "CEP inválido: " + message;
            default:
                return field + " inválido: " + message;
        }
    }
}
