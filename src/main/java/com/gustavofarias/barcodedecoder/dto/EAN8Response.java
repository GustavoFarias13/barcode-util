package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public record EAN8Response (
        BarcodeType encodingType,
        String barcode,
        String prefixInfo,
        String product,
        String checkDigit
) implements BarcodeDecodedResponse{}