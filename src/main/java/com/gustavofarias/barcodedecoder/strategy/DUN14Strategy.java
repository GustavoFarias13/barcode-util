package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.DUN14Response;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
//import com.gustavofarias.barcodedecoder.util.BarcodeNormalizer;
import org.springframework.stereotype.Component;

@Component
public class DUN14Strategy implements BarcodeStrategy {

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.DUN14;
    }

    @Override
    public boolean isValid(String barcode) {
//        if (getCodificationType().requiresNormalization()) {
//            barcode = BarcodeNormalizer.normalize(barcode);
//        }

        return barcode != null && barcode.matches("\\d{14}");
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var indicatorDigit = String.valueOf(barcode.charAt(0));
        var gtinBase = barcode.substring(1, 13);
        var checkDigit = String.valueOf(barcode.charAt(13));

        return new DUN14Response(
                getCodificationType(),
                barcode,
                indicatorDigit,
                gtinBase,
                checkDigit
        );
    }
}