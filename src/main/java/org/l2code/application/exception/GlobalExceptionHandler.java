package org.l2code.application.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpacotamentoException.class)
    public ResponseEntity<ErrorResponse> handleEmpacotamentoException(EmpacotamentoException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("EMPACOTAMENTO_ERROR", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("INTERNAL_ERROR", "Erro interno inesperado."));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof MismatchedInputException mismatched) {
            String path = mismatched.getPathReference();
            String message = "Tipo inválido no campo " + path;
            return ResponseEntity.unprocessableEntity().body(Map.of("erro", message));
        }

        return ResponseEntity.unprocessableEntity().body(Map.of("erro", "Campos Inválidos, verifique os campos."));
    }
}
