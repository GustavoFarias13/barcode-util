package com.gustavofarias.barcodedecoder.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UPCAResponse implements BarcodeDecodedResponse{

    private String barcode;
    private boolean valid;
    private String systemNumber;
    private String systemDescription;
    private String manufacturerCode;
    private String productCode;
    private String checkDigit;
}
