package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.model.BarcodeType;

public interface BarcodeStrategy {
    BarcodeType getCodificationType();
    boolean isValid(String code);
    BarcodeDecodedResponse decode(String code);
}
