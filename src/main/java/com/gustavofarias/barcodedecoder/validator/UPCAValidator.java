package com.gustavofarias.barcodedecoder.validator;

import com.gustavofarias.barcodedecoder.exception.BarcodeParsingException;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;

public class UPCAValidator implements BarcodeValidator {

    @Override
    public boolean validate(String code) {
        if (code == null || code.isBlank()) {
            throw new InvalidBarcodeException("Barcode cannot be null or empty");
        }

        if (!code.matches("\\d{12}")) {
            throw new InvalidBarcodeException("The barcode must contain exactly 12 numeric digits");
        }

        return calculateChecksum(code) == Character.getNumericValue(code.charAt(11));
    }

    private int calculateChecksum(String code) {
        try {
            int sum = 0;
            for (int i = 0; i < 11; i++) {
                int digit = Character.getNumericValue(code.charAt(i));
                sum += (i % 2 == 0) ? digit * 3 : digit;
            }
            return (10 - (sum % 10)) % 10;
        } catch (Exception e) {
            throw new BarcodeParsingException("Error calculating UPC check digit", e);
        }
    }
}
