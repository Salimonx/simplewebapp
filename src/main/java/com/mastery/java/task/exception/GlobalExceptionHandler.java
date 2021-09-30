package com.mastery.java.task.exception;

import com.mastery.java.task.dto.ExceptionResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ExceptionResp> employeeNotFoundExeption(BusinessException exeption) {
        return ExceptionResp.of(exeption.getMessage(), exeption.getCode()).getResponseEntity();
    }

}
