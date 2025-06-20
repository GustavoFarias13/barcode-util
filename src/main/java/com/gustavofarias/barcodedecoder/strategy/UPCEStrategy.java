package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.UPCEResponse;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.repository.SystemNumbersRepository;
import org.springframework.stereotype.Component;

@Component
public class UPCEStrategy implements BarcodeStrategy {

    private final SystemNumbersRepository systemNumbersRepository;

    public UPCEStrategy(SystemNumbersRepository systemNumbersRepository) {
        this.systemNumbersRepository = systemNumbersRepository;
    }

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.UPCE;
    }

    @Override
    public boolean isValid(String barcode) {
//        if (getCodificationType().requiresNormalization()) {
//            barcode = BarcodeNormalizer.normalize(barcode);
//        }

        return barcode != null && barcode.matches("\\d{12}");
    }

    // TODO 04210005, 01234565 and 06510005
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var systemNumber = Integer.parseInt(barcode.substring(0, 1));
        var compressedBody = barcode.substring(1, 7);
        var checkDigit = barcode.substring(7);


        var systemNumberOpt = systemNumbersRepository.findFirstByNumber(systemNumber)
                .orElseThrow(() -> new BarcodeNotFoundException("System number not found for code: " + systemNumber));
        var systemNumberInfo = systemNumber + " - " + systemNumberOpt.getDescription();

        return new UPCEResponse(
                getCodificationType(),
                barcode,
                systemNumberInfo,
                compressedBody,
                checkDigit
        );
    }
}
