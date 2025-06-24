package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

/**
 * A response DTO representing a decoded UPC-A barcode.
 * <p>
 * UPC-A is a 12-digit barcode widely used in North America for retail products.
 * This record exposes its main structural components.
 *
 * @param encodingType      The barcode encoding type (UPC-A)
 * @param barcode           The full original UPC-A barcode string
 * @param systemNumberInfo  The system number or prefix that identifies the product category or usage
 * @param manufacturer      The manufacturer's identification number
 * @param product           The product/item code assigned by the manufacturer
 * @param checkDigit        The check digit used for error detection (last digit)
 */
public record UPCAResponse (
        BarcodeType encodingType,
        String barcode,
        String systemNumberInfo,
        String manufacturer,
        String product,
        String checkDigit
) implements BarcodeDecodedResponse {}
