package com.gustavofarias.barcodedecoder.exception;

public class BarcodeParsingException extends BarcodeException {
    public BarcodeParsingException(String message, Throwable cause) {
        super(message, "PARSE_ERROR", cause);
    }
}
