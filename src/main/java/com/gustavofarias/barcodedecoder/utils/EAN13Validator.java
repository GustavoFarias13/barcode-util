package com.gustavofarias.barcodedecoder.utils;

import com.gustavofarias.barcodedecoder.exception.BarcodeParsingException;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;

public class EAN13Validator {

    public static boolean validate(String code) {
        if (code == null || code.isBlank()) {
            throw new InvalidBarcodeException("Barcode cannot be null or empty");
        }

        if (!code.matches("\\d{13}")) {
            throw new InvalidBarcodeException("The barcode must contain exactly 13 numeric digits");
        }

        return calculateChecksum(code) == Character.getNumericValue(code.charAt(12));
    }

    private static int calculateChecksum(String code) {
        try {
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Character.getNumericValue(code.charAt(i));
                sum += (i % 2 == 0) ? digit : digit * 3;
            }
            return (10 - (sum % 10)) % 10;
        } catch (Exception e) {
            throw new BarcodeParsingException("Error calculating check digit", e);
        }
    }
}
