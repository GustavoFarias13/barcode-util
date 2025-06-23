package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public record UPCEResponse(
        BarcodeType encodingType,
        String barcode,
        String systemNumberInfo,
        String compressBody,
        String checkDigit
) implements BarcodeDecodedResponse {}