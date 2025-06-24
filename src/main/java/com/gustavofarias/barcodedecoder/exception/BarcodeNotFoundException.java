package com.gustavofarias.barcodedecoder.exception;

/**
 * Exception thrown when a requested barcode is not found or cannot be decoded.
 * <p>
 * This is a specific type of {@link BarcodeException} with an error code "NOT_FOUND".
 */
public class BarcodeNotFoundException extends BarcodeException {

    /**
     * Constructs a new BarcodeNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the barcode was not found
     */
    public BarcodeNotFoundException(String message) {
        super(message, "NOT_FOUND");
    }
}
