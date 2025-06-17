package com.gustavofarias.barcodedecoder.decoder;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.EAN8Response;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.repository.PrefixRepository;
import org.springframework.stereotype.Component;

@Component
public class EAN8Decoder implements BarcodeDecoder {

    private final PrefixRepository prefixRepository;

    public EAN8Decoder(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var prefix = barcode.substring(0, 3);
        var productCode = barcode.substring(3, 7);
        var checkDigit = barcode.substring(7);

        var prefixOpt = prefixRepository.findFirstByCode(Integer.parseInt(prefix));
        if (prefixOpt.isEmpty()) {
            throw new BarcodeNotFoundException("Prefix not found for code: " + prefix);
        }

        var country = prefixOpt.get().getCountry();

        return EAN8Response.builder()
                .barcode(barcode)
                .valid(true)
                .prefixInfo(prefix + " - " + country)
                .product(productCode)
                .checkDigit(checkDigit)
                .build();
    }

}
