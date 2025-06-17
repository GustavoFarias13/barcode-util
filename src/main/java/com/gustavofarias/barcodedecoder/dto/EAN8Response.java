package com.gustavofarias.barcodedecoder.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EAN8Response implements BarcodeDecodedResponse {

    private String barcode;
    private boolean valid;
    private String prefix;
    private String country;
    private String productCode;
    private String checkDigit;
}
