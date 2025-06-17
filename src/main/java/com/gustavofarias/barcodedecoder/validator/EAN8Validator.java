package com.gustavofarias.barcodedecoder.validator;

import com.gustavofarias.barcodedecoder.exception.BarcodeParsingException;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;

public class EAN8Validator implements BarcodeValidator {

    @Override
    public boolean validate(String code) {
        if (code == null || code.isBlank()) {
            throw new InvalidBarcodeException("Barcode cannot be null or empty");
        }

        if (!code.matches("\\d{8}")) {
            throw new InvalidBarcodeException("The barcode must contain exactly 8 numeric digits");
        }

        return calculateChecksum(code) == Character.getNumericValue(code.charAt(7));
    }

    private int calculateChecksum(String code) {
        try {
            int sum = 0;
            for (int i = 0; i < 7; i++) {
                int digit = Character.getNumericValue(code.charAt(i));
                sum += (i % 2 == 0) ? digit * 3 : digit;
            }
            return (10 - (sum % 10)) % 10;
        } catch (Exception e) {
            throw new BarcodeParsingException("Error calculating EAN-8 check digit", e);
        }
    }
}
