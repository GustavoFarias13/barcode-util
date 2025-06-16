package com.gustavofarias.barcodedecoder.service;

import com.gustavofarias.barcodedecoder.dto.BarcodeResponse;
import com.gustavofarias.barcodedecoder.repository.PrefixRepository;
import com.gustavofarias.barcodedecoder.utils.EAN13Validator;
import org.springframework.stereotype.Service;

@Service
public class BarcodeService {

    private final PrefixRepository prefixRepository;

    public BarcodeService(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    public BarcodeResponse decodeBarcode(String code) {

        if (!code.matches("\\d{13}")) {
            return BarcodeResponse.builder()
                    .codigo(code)
                    .valido(false)
                    .erro("O código deve conter exatamente 13 dígitos.")
                    .build();
        }

        var isValid = EAN13Validator.validate(code);

        var prefixStr = code.substring(0, 3);
        var fabricante = code.substring(3, 7);
        var produto = code.substring(7, 12);
        var verificador = code.substring(12);

        var prefixInt = Integer.parseInt(prefixStr);
        var paisOpt = prefixRepository.findCountryByPrefixCode(prefixInt);

        return BarcodeResponse.builder()
                .codigo(code)
                .valido(isValid)
                .prefixoPais(prefixStr)
                .pais(paisOpt.orElse("Desconhecido"))
                .codigoFabricante(fabricante)
                .codigoProduto(produto)
                .digitoVerificador(verificador)
                .build();
    }

}
