package com.gustavofarias.barcodedecoder.exception;

import com.gustavofarias.barcodedecoder.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for the application.
 * Handles all exceptions and returns appropriate responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBarcodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBarcode(InvalidBarcodeException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(BarcodeParsingException.class)
    public ResponseEntity<ErrorResponse> handleParsingError(BarcodeParsingException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURI());
    }

    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseError(DatabaseAccessException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }

    @ExceptionHandler(BarcodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(BarcodeNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(BarcodeException.class)
    public ResponseEntity<ErrorResponse> handleGenericBarcodeError(BarcodeException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedError(Exception ex, HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Unexpected internal error: " + ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(BarcodeException ex, HttpStatus status, String path) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(ex.getMessage())
                .code(ex.getErrorCode())
                .path(path)
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
