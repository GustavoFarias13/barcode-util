package com.gustavofarias.barcodedecoder.utils;

public class EAN13Validator {
    public static boolean validate(String code) {
        var sum = 0;
        for (var i = 0; i < 12; i++) {
            var digit = Character.getNumericValue(code.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        var checksum = (10 - (sum % 10)) % 10;
        return checksum == Character.getNumericValue(code.charAt(12));
    }
}
