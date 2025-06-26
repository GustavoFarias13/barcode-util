package com.gustavofarias.barcodeutil.exception;

/**
 * Exception thrown when a requested barcode cannot be generated.
 * <p>
 * This is a specific type of {@link BarcodeException} with an error code "GENERATE_ERROR".
 */
public class BarcodeNotGeneratedException extends BarcodeException {

    /**
     * Constructs a new BarcodeNotGeneratedException with the specified detail message.
     *
     * @param message the detail message explaining why the barcode generation failed
     */
    public BarcodeNotGeneratedException(String message) {
        super(message, "GENERATE_ERROR");
    }
}
