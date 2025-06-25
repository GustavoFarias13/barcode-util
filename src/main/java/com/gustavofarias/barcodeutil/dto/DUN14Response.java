package com.gustavofarias.barcodeutil.dto;

import com.gustavofarias.barcodeutil.model.BarcodeType;

/**
 * A response DTO representing a decoded DUN-14 (Distribution Unit Number) barcode.
 * <p>
 * DUN-14 is a 14-digit GTIN used to identify trade item groupings (e.g., cartons or pallets).
 * This DTO extracts and exposes its individual components for further processing or display.
 *
 * @param encodingType   The barcode encoding type (should be DUN-14)
 * @param barcode        The original raw barcode string
 * @param indicatorDigit The packaging level indicator (1 digit)
 * @param gtinBase       The base GTIN (12 digits, excluding indicator and check digit)
 * @param checkDigit     The calculated check digit (1 digit)
 */
public record DUN14Response (
        BarcodeType encodingType,
        String barcode,
        String indicatorDigit,
        String gtinBase,
        String checkDigit
) implements BarcodeDecodedResponse {}
