package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

/**
 * A response DTO representing a decoded UPC-E barcode.
 * <p>
 * UPC-E is a compressed 6-digit version of the UPC barcode, used on small packages.
 * This record exposes its main parts after decoding.
 *
 * @param encodingType    The barcode encoding type (UPC-E)
 * @param barcode         The original scanned UPC-E barcode string
 * @param systemNumberInfo The system number or prefix information
 * @param compressBody    The compressed body of the UPC-E code (main product code)
 * @param checkDigit      The check digit used for validation (last digit)
 */
public record UPCEResponse(
        BarcodeType encodingType,
        String barcode,
        String systemNumberInfo,
        String compressBody,
        String checkDigit
) implements BarcodeDecodedResponse {}
