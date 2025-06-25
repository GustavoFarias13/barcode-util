package com.gustavofarias.barcodeutil.dto;

import java.time.LocalDateTime;

/**
 * A standard structure for API error responses.
 * <p>
 * This record is typically used when an exception or validation failure occurs,
 * allowing the client to receive meaningful and structured error information.
 *
 * @param status    The HTTP status code (e.g., 400, 404, 500)
 * @param error     A short error label (e.g., "Bad Request", "Internal Server Error")
 * @param message   A detailed error message (e.g., "Barcode is invalid")
 * @param timestamp The time when the error occurred
 */
public record ErrorResponse(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {}
