package com.gustavofarias.barcodedecoder.exception;

/**
 * Exception thrown when an error occurs during barcode parsing or decoding.
 * <p>
 * This exception indicates a failure to interpret the barcode string correctly,
 * and it carries the error code "PARSE_ERROR".
 */
public class BarcodeParsingException extends BarcodeException {

    /**
     * Constructs a new BarcodeParsingException with the specified detail message and cause.
     *
     * @param message the detail message explaining the parsing error
     * @param cause   the underlying cause of the parsing failure (e.g., malformed input)
     */
    public BarcodeParsingException(String message, Throwable cause) {
        super(message, "PARSE_ERROR", cause);
    }
}
