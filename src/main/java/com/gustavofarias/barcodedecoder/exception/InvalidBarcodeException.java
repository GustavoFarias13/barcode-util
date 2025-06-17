package com.gustavofarias.barcodedecoder.exception;

public class InvalidBarcodeException extends BarcodeException {
    public InvalidBarcodeException(String message) {
        super(message, "INVALID_BARCODE");
    }
}
