package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public record EAN13Response (
        BarcodeType encodingType,
        String barcode,
        String prefixInfo,
        String manufacturer,
        String product,
        String checkDigit
) implements BarcodeDecodedResponse {}
