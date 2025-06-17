package com.gustavofarias.barcodedecoder.util;

public class BarcodeNormalizer {

    public static String normalize(String rawCode) {
        if (rawCode == null || rawCode.isBlank()) {
            throw new IllegalArgumentException("Barcode cannot be null or empty");
        }

        // Remove tudo que não for dígito
        String cleaned = rawCode.replaceAll("\\D", "");

        // Verifica tamanho válido para os três modelos
        if (cleaned.length() == 8 || cleaned.length() == 12 || cleaned.length() == 13) {
            return cleaned;
        } else {
            return rawCode;
        }
    }
}
