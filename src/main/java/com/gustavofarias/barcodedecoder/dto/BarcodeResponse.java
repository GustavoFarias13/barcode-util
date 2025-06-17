package com.gustavofarias.barcodedecoder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarcodeResponse {

    private String barcode;
    private boolean valid;
    private String prefix;
    private String country;
    private String manufacturerCode;
    private String productCode;
    private String checkDigit;

}
