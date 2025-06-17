package com.gustavofarias.barcodedecoder.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EAN13Response implements BarcodeDecodedResponse {

    private String barcode;
    private boolean valid;
    private String prefix;
    private String country;
    private String manufacturerCode;
    private String productCode;
    private String checkDigit;
}
