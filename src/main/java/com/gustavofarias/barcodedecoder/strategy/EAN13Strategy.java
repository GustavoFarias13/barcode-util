package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.EAN13Response;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.repository.PrefixRepository;
import com.gustavofarias.barcodedecoder.util.BarcodeNormalizer;
import org.springframework.stereotype.Component;

@Component
public class EAN13Strategy implements BarcodeStrategy {

    private final PrefixRepository prefixRepository;

    public EAN13Strategy(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.EAN13;
    }

    @Override
    public boolean isValid(String barcode) {
//        if (getCodificationType().requiresNormalization()) {
//            barcode = BarcodeNormalizer.normalize(barcode);
//        }

        return barcode != null && barcode.matches("\\d{13}");
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var prefix = Integer.parseInt(barcode.substring(0, 3));
        var manufacturer = barcode.substring(3, 7);
        var product = barcode.substring(7, 12);
        var checkDigit = barcode.substring(12);

        var prefixOpt = prefixRepository.findFirstByCode(prefix)
                .orElseThrow(() -> new BarcodeNotFoundException("Prefix not found: " + prefix));
        var prefixInfo = prefix + " - " + prefixOpt.getCountry();

        return new EAN13Response(
                getCodificationType(),
                barcode,
                prefixInfo,
                manufacturer,
                product,
                checkDigit
        );
    }
}
