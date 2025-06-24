package com.gustavofarias.barcodedecoder.exception;

/**
 * Exception thrown when a database access error occurs during barcode operations.
 * <p>
 * This exception indicates issues interacting with the database, such as connection failures,
 * query errors, or transaction problems. It carries the error code "DB_ERROR".
 */
public class DatabaseAccessException extends BarcodeException {

    /**
     * Constructs a new DatabaseAccessException with the specified detail message and cause.
     *
     * @param message the detail message explaining the database error
     * @param cause   the underlying cause of the database access failure
     */
    public DatabaseAccessException(String message, Throwable cause) {
        super(message, "DB_ERROR", cause);
    }
}
