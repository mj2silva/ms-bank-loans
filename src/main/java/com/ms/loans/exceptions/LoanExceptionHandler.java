package com.ms.loans.exceptions;

import com.ms.restUtilities.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class LoanExceptionHandler {

    @ExceptionHandler(LoanTransactionException.class)
    public ResponseEntity<ErrorResponseDto> handleLoanTransactionException(LoanTransactionException ex, WebRequest request) {
        var errorResponse = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoanConflictException.class)
    public ResponseEntity<ErrorResponseDto> handleLoanConflictException(LoanConflictException ex, WebRequest request) {
        var errorResponse = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
