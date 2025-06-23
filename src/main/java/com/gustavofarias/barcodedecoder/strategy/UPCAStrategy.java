package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.UPCAResponse;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.repository.SystemNumbersRepository;
import com.gustavofarias.barcodedecoder.util.BarcodeNormalizer;
import org.springframework.stereotype.Component;

@Component
public class UPCAStrategy implements BarcodeStrategy {

    private final SystemNumbersRepository systemNumbersRepository;

    public UPCAStrategy(SystemNumbersRepository systemNumbersRepository) {
        this.systemNumbersRepository = systemNumbersRepository;
    }

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.UPCA;
    }

    @Override
    public boolean isValid(String barcode) {
//        if (getCodificationType().requiresNormalization()) {
//            barcode = BarcodeNormalizer.normalize(barcode);
//        }

        return barcode != null && barcode.matches("\\d{12}");
    }

    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var systemNumber = Integer.parseInt(barcode.substring(0, 1));
        var manufacturer = barcode.substring(1, 6);
        var product = barcode.substring(6, 11);
        var checkDigit = barcode.substring(11);

        var systemNumberOpt = systemNumbersRepository.findFirstByNumber(systemNumber)
                .orElseThrow(() -> new BarcodeNotFoundException("System number not found for code: " + systemNumber));
        var systemNumberInfo = systemNumber + " - " + systemNumberOpt.getDescription();

        return new UPCAResponse(
                getCodificationType(),
                barcode,
                systemNumberInfo,
                manufacturer,
                product,
                checkDigit
        );
    }
}
