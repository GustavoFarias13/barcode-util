package com.gustavofarias.barcodeutil.dto.gs1;

/**
 * A record representing a decoded GS1 Application Identifier (AI) field.
 *
 * @param code        The Application Identifier (e.g., "01", "10", "17")
 * @param description A human-readable description of the AI (e.g., "GTIN", "Batch Number")
 * @param value       The value associated with the AI (e.g., "01234567890128", "ABC123")
 */
public record GS1Fields(
        String code,
        String description,
        String value
) {}
