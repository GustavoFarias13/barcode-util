package com.gustavofarias.barcodedecoder.exception;

public class BarcodeNotFoundException extends BarcodeException {
    public BarcodeNotFoundException(String message) {
        super(message, "NOT_FOUND");
    }
}
