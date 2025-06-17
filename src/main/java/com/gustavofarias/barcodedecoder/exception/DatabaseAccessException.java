package com.gustavofarias.barcodedecoder.exception;

public class DatabaseAccessException extends BarcodeException {
    public DatabaseAccessException(String message, Throwable cause) {
        super(message, "DB_ERROR", cause);
    }
}
