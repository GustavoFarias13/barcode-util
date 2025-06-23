package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.EAN8Response;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.repository.PrefixRepository;
import com.gustavofarias.barcodedecoder.util.BarcodeNormalizer;
import org.springframework.stereotype.Component;

@Component
public class EAN8Strategy implements BarcodeStrategy {

    private final PrefixRepository prefixRepository;

    public EAN8Strategy(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.EAN8;
    }

    @Override
    public boolean isValid(String barcode) {
//        if (getCodificationType().requiresNormalization()) {
//            barcode = BarcodeNormalizer.normalize(barcode);
//        }

        return barcode != null && barcode.matches("\\d{8}");
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var prefix = Integer.parseInt(barcode.substring(0, 3));
        var product = barcode.substring(3, 7);
        var checkDigit = barcode.substring(7);

        var prefixOpt = prefixRepository.findFirstByCode(prefix)
                .orElseThrow(() -> new BarcodeNotFoundException("Prefix not found: " + prefix));
        var prefixInfo = prefix + " - " + prefixOpt.getCountry();

        return new EAN8Response(
                getCodificationType(),
                barcode,
                prefixInfo,
                product,
                checkDigit
        );
    }
}
