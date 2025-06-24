package com.gustavofarias.barcodedecoder.exception;

import lombok.Getter;

/**
 * Custom runtime exception class for barcode processing errors.
 * <p>
 * This exception carries an additional error code alongside the message,
 * allowing more precise error handling or categorization.
 */
@Getter
public class BarcodeException extends RuntimeException {

    /**
     * A custom error code that helps identify the type or category of the barcode error.
     */
    private final String errorCode;

    /**
     * Constructs a new BarcodeException with the specified detail message and error code.
     *
     * @param message   The detail message explaining the error.
     * @param errorCode A custom error code representing the error type.
     */
    public BarcodeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new BarcodeException with the specified detail message, error code, and cause.
     *
     * @param message   The detail message explaining the error.
     * @param errorCode A custom error code representing the error type.
     * @param cause     The underlying cause of the exception.
     */
    public BarcodeException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
