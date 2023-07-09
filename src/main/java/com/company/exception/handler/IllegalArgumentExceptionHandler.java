package com.company.exception.handler;

import com.company.controller.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class IllegalArgumentExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, status);
    }
}
