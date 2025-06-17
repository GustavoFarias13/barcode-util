package com.gustavofarias.barcodedecoder.decoder;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.DUN14Response;
import org.springframework.stereotype.Component;

@Component
public class DUN14Decoder implements BarcodeDecoder {

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var indicatorDigit = String.valueOf(barcode.charAt(0));
        var gtinBase = barcode.substring(1, 13);
        var checkDigit = String.valueOf(barcode.charAt(13));

        return DUN14Response.builder()
                .barcode(barcode)
                .valid(true)
                .indicatorDigit(indicatorDigit)
                .gtinBase(gtinBase)
                .checkDigit(checkDigit)
                .build();
    }
}