package com.gustavofarias.barcodeutil.dto;

import com.gustavofarias.barcodeutil.model.BarcodeType;

/**
 * A response DTO representing a decoded EAN-8 barcode.
 * <p>
 * EAN-8 is a short 8-digit barcode used for small packages where space is limited.
 * This class exposes its key structural parts.
 *
 * @param encodingType The barcode encoding type (EAN-8)
 * @param barcode      The full original EAN-8 barcode string
 * @param prefixInfo   The prefix that identifies the country or numbering system (typically 2–3 digits)
 * @param product      The product or item reference (typically 4–5 digits)
 * @param checkDigit   The check digit used for validation (last digit)
 */
public record EAN8Response (
        BarcodeType encodingType,
        String barcode,
        String prefixInfo,
        String product,
        String checkDigit
) implements BarcodeDecodedResponse {}
