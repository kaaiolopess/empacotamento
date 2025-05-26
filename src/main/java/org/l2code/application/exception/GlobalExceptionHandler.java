/*
package org.l2code.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
*/
