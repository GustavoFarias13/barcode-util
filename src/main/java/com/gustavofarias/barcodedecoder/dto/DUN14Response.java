package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DUN14Response implements BarcodeDecodedResponse {

    private final String encodingType = String.valueOf(BarcodeType.DUN_14);
    private final String barcode;
    private final boolean valid;
    private final String indicatorDigit;
    private final String gtinBase;
    private final String checkDigit;

}
