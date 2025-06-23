package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public record DUN14Response (
        BarcodeType encodingType,
        String barcode,
        String indicatorDigit,
        String gtinBase,
        String checkDigit
) implements BarcodeDecodedResponse{}
