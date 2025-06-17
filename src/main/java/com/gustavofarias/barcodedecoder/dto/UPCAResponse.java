package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;
import lombok.*;

@Getter
@Builder
public class UPCAResponse implements BarcodeDecodedResponse{

    private final String encodingType = String.valueOf(BarcodeType.UPC_A);
    private final String barcode;
    private final boolean valid;
    private final String systemNumberInfo;
    private final String manufacturer;
    private final String product;
    private final String checkDigit;

}
