package com.gustavofarias.barcodedecoder.validator;

import com.gustavofarias.barcodedecoder.exception.BarcodeParsingException;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;
import org.springframework.stereotype.Component;

@Component
public class DUN14Validator implements BarcodeValidator {

    @Override
    public boolean validate(String code) {
        if (code == null || !code.matches("\\d{14}")) {
            throw new InvalidBarcodeException("The barcode must contain exactly 14 numeric digits");
        }

        return calculateChecksum(code) == Character.getNumericValue(code.charAt(13));
    }

    private int calculateChecksum(String code) {
        try {
            int sum = 0;
            for (int i = 0; i < 13; i++) {
                int digit = Character.getNumericValue(code.charAt(i));
                sum += (i % 2 == 0) ? digit * 3 : digit;
            }
            return (10 - (sum % 10)) % 10;
        } catch (Exception e) {
            throw new BarcodeParsingException("Error calculating check digit", e);
        }
    }
}
