package com.gustavofarias.barcodedecoder.decoder;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.EAN13Response;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.repository.PrefixRepository;
import org.springframework.stereotype.Component;

@Component
public class EAN13Decoder implements BarcodeDecoder {

    private final PrefixRepository prefixRepository;

    public EAN13Decoder(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var prefixStr = barcode.substring(0, 3);
        var manufacturer = barcode.substring(3, 7);
        var product = barcode.substring(7, 12);
        var checker = barcode.substring(12);

        var prefixOpt = prefixRepository.findFirstByCode(Integer.parseInt(prefixStr));
        if (prefixOpt.isEmpty()) {
            throw new BarcodeNotFoundException("Prefix not found for code: " + prefixStr);
        }

        var country = prefixOpt.get().getCountry();

        return EAN13Response.builder()
                .barcode(barcode)
                .valid(true)
                .prefix(prefixStr)
                .country(country)
                .manufacturerCode(manufacturer)
                .productCode(product)
                .checkDigit(checker)
                .build();
    }
}
