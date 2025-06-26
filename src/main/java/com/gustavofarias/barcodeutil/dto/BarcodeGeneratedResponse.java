package com.gustavofarias.barcodeutil.dto;

import com.gustavofarias.barcodeutil.model.BarcodeType;

/**
 * Represents the response returned after successfully generating a barcode image.
 * Contains the type of barcode encoding used, the original barcode data,
 * and the generated barcode image encoded as a Base64 string.
 *
 * @param encodingType the type of barcode encoding used
 * @param barcode      the original barcode data string
 * @param base64Image  the generated barcode image encoded in Base64 PNG format
 */
public record BarcodeGeneratedResponse(
        BarcodeType encodingType,
        String barcode,
        String base64Image
) {}
