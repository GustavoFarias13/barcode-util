package com.gustavofarias.barcodedecoder.util;

import com.gustavofarias.barcodedecoder.validator.BarcodeValidatorFactory;

public class BarcodeValidationUtil {

    public static boolean validateWithAutoDetection(String code) {
        return BarcodeValidatorFactory.getValidator(code).validate(code);
    }
}
