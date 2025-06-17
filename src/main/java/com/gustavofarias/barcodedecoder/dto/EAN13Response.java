package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EAN13Response implements BarcodeDecodedResponse {

    private final String barcode;
    private final boolean valid;
    private final String prefix;
    private final String country;
    private final String manufacturerCode;
    private final String productCode;
    private final String checkDigit;

    @Override
    public BarcodeType getBarcodeType() {
        return BarcodeType.EAN_13;
    }
}
