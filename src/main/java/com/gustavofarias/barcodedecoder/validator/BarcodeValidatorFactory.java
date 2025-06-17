package com.gustavofarias.barcodedecoder.validator;

import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.util.BarcodeTypeDetector;

public class BarcodeValidatorFactory {

    public static BarcodeValidator getValidator(String code) {
        BarcodeType type = BarcodeTypeDetector.detectType(code);

        return switch (type) {
            case EAN_13 -> new EAN13Validator();
            case UPC_A -> new UPCAValidator();
            case EAN_8 -> new EAN8Validator();
            default -> throw new InvalidBarcodeException("Unsupported or unrecognized barcode format");
        };
    }
}
