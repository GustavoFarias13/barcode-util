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
        var prefix = barcode.substring(0, 3);
        var manufacturer = barcode.substring(3, 7);
        var product = barcode.substring(7, 12);
        var checker = barcode.substring(12);

        var prefixOpt = prefixRepository.findFirstByCode(Integer.parseInt(prefix));
        if (prefixOpt.isEmpty()) {
            throw new BarcodeNotFoundException("Prefix not found for code: " + prefix);
        }

        var country = prefixOpt.get().getCountry();

        return EAN13Response.builder()
                .barcode(barcode)
                .valid(true)
                .prefixInfo(prefix + " - " + country)
                .manufacturer(manufacturer)
                .product(product)
                .checkDigit(checker)
                .build();
    }

}
