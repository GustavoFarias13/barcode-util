package com.gustavofarias.barcodedecoder.utils;

import java.util.Map;

public class CountryCodeMapper {
    private static final Map<String, String> countryCodes = Map.of(
            "789", "Brasil",
            "790", "Brasil",
            "560", "Portugal",
            "000", "EUA/Canadá"
            // Adicione mais prefixos conforme necessário
    );

    public static String getCountry(String prefix) {
        return countryCodes.getOrDefault(prefix, "Desconhecido");
    }
}
