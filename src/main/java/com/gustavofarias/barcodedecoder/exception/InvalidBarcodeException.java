package com.gustavofarias.barcodedecoder.exception;

/**
 * Exception thrown when a barcode is invalid or malformed.
 * <p>
 * This is a specific subtype of {@link BarcodeException} with the error code "INVALID_BARCODE".
 */
public class InvalidBarcodeException extends BarcodeException {

    /**
     * Constructs a new InvalidBarcodeException with the specified detail message.
     *
     * @param message the detail message describing why the barcode is invalid
     */
    public InvalidBarcodeException(String message) {
        super(message, "INVALID_BARCODE");
    }
}
