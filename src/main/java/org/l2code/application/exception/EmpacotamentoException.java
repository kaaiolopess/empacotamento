package org.l2code.application.exception;

public class EmpacotamentoException extends RuntimeException {
    public EmpacotamentoException(String message) {
        super(message);
    }

    public EmpacotamentoException(String message, Throwable cause) {
        super(message, cause);
    }
}

