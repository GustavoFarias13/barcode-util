package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EAN8Response implements BarcodeDecodedResponse {

    private final String encodingType = String.valueOf(BarcodeType.EAN_8);
    private final String barcode;
    private final boolean valid;
    private final String prefixInfo;
    private final String product;
    private final String checkDigit;

}
