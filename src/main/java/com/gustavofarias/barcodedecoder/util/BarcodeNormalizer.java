package com.gustavofarias.barcodedecoder.util;

public class BarcodeNormalizer {

    public static String normalize(String rawCode) {
        if (rawCode == null || rawCode.isBlank()) {
            throw new IllegalArgumentException("Barcode cannot be null or empty");
        }

        return rawCode.replaceAll("\\D", ""); // Remove tudo que não for número
    }
}
