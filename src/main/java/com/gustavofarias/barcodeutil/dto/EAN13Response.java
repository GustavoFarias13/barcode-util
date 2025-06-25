package com.gustavofarias.barcodeutil.dto;

import com.gustavofarias.barcodeutil.model.BarcodeType;

/**
 * A response DTO representing a decoded EAN-13 barcode.
 * <p>
 * EAN-13 is a globally used 13-digit barcode standard for identifying products.
 * This record extracts and exposes the key parts of the EAN-13 structure.
 *
 * @param encodingType  The barcode encoding type (EAN-13)
 * @param barcode       The full EAN-13 barcode string
 * @param prefixInfo    The country prefix or numbering system (first 2–3 digits)
 * @param manufacturer  The manufacturer's identification code
 * @param product       The product/item reference assigned by the manufacturer
 * @param checkDigit    The calculated check digit (13th digit used for validation)
 */
public record EAN13Response (
        BarcodeType encodingType,
        String barcode,
        String prefixInfo,
        String manufacturer,
        String product,
        String checkDigit
) implements BarcodeDecodedResponse {}
