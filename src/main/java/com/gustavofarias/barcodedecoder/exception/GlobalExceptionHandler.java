package com.gustavofarias.barcodedecoder.exception;

import com.gustavofarias.barcodedecoder.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BarcodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(BarcodeNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Barcode not found", ex);
    }

    @ExceptionHandler(InvalidBarcodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidBarcodeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid barcode", ex);
    }

    @ExceptionHandler(BarcodeParsingException.class)
    public ResponseEntity<ErrorResponse> handleParsing(BarcodeParsingException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Error interpreting barcode", ex);
    }

    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabase(DatabaseAccessException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected internal error", ex);
    }

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
