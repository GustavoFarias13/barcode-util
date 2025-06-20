package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public record Code128Response(
        BarcodeType encodingType,
        String barcode,
        String rawData,
        String charsetUsed
) implements BarcodeDecodedResponse {}
