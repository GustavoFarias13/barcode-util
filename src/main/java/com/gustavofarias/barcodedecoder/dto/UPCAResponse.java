package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public record UPCAResponse (
        BarcodeType encodingType,
        String barcode,
        String systemNumberInfo,
        String manufacturer,
        String product,
        String checkDigit
) implements BarcodeDecodedResponse {}