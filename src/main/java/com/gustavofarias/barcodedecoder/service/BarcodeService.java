package com.gustavofarias.barcodedecoder.service;

import com.gustavofarias.barcodedecoder.utils.CountryCodeMapper;
import com.gustavofarias.barcodedecoder.utils.EAN13Validator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BarcodeService {

    public Map<String, Object> decodeBarcode(String code) {
        Map<String, Object> result = new HashMap<>();
        result.put("codigo", code);

        if (!code.matches("\\d{13}")) {
            result.put("valido", false);
            result.put("erro", "O código deve conter exatamente 13 dígitos.");
            return result;
        }

        boolean isValid = EAN13Validator.validate(code);
        result.put("valido", isValid);

        String prefix = code.substring(0, 3);
        String fabricante = code.substring(3, 7);
        String produto = code.substring(7, 12);
        String verificador = code.substring(12);

        result.put("pais", CountryCodeMapper.getCountry(prefix));
        result.put("prefixo_pais", prefix);
        result.put("codigo_fabricante", fabricante);
        result.put("codigo_produto", produto);
        result.put("digito_verificador", verificador);

        return result;
    }
}
