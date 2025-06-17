package com.gustavofarias.barcodedecoder.exception;

import lombok.Getter;

/**
 * Custom exception for barcode-related errors.
 */
@Getter
public class BarcodeException extends RuntimeException {

    private final String errorCode;

    public BarcodeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BarcodeException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
