package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

/**
 * A response DTO that represents a decoded CODE 128 barcode.
 * <p>
 * Implements the {@link BarcodeDecodedResponse} interface to allow
 * polymorphic handling of various barcode decoding results.
 *
 * @param encodingType The type of barcode encoding (should be CODE_128)
 * @param barcode      The original raw barcode string
 * @param length       The length of the barcode content
 */
public record Code128Response(
        BarcodeType encodingType,
        String barcode,
        int length
) implements BarcodeDecodedResponse {}
