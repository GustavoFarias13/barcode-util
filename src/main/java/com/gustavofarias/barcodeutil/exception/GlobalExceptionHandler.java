package com.gustavofarias.barcodeutil.exception;

import com.gustavofarias.barcodeutil.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Global exception handler for the barcode decoding API.
 * <p>
 * This class uses Spring's @RestControllerAdvice to intercept exceptions
 * thrown by controllers and convert them into meaningful HTTP responses
 * with structured error payloads.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles BarcodeNotFoundException, returning HTTP 404 Not Found.
     */
    @ExceptionHandler(BarcodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(BarcodeNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Barcode not found", ex);
    }

    /**
     * Handles InvalidBarcodeException, returning HTTP 400 Bad Request.
     */
    @ExceptionHandler(InvalidBarcodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidBarcodeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid barcode", ex);
    }

    /**
     * Handles BarcodeParsingException, returning HTTP 400 Bad Request.
     */
    @ExceptionHandler(BarcodeParsingException.class)
    public ResponseEntity<ErrorResponse> handleParsing(BarcodeParsingException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Error interpreting barcode", ex);
    }

    /**
     * Handles DatabaseAccessException, returning HTTP 500 Internal Server Error.
     */
    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabase(DatabaseAccessException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", ex);
    }

    /**
     * Handles any other uncaught exceptions, returning HTTP 500 Internal Server Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected internal error", ex);
    }

    /**
     * Helper method to build a standardized ErrorResponse wrapped in a ResponseEntity.
     *
     * @param status  the HTTP status code to return
     * @param message a short message summarizing the error
     * @param ex      the exception that triggered the error
     * @return a ResponseEntity containing the ErrorResponse and HTTP status
     */
    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, Exception ex) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                message,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}
