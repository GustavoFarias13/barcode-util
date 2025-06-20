package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.Code128Response;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import org.springframework.stereotype.Component;

@Component
public class Code128Strategy implements BarcodeStrategy {

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.CODE128;
    }

    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.matches("[\\x20-\\x7E]+");
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        return new Code128Response(
                getCodificationType(),
                barcode,
                barcode.length()  // apenas exemplo
        );
    }
}
