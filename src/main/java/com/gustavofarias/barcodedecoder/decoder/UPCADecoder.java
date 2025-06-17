package com.gustavofarias.barcodedecoder.decoder;

import com.gustavofarias.barcodedecoder.dto.UPCAResponse;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.repository.SystemNumbersRepository;
import org.springframework.stereotype.Component;

@Component
public class UPCADecoder implements BarcodeDecoder {

    private final SystemNumbersRepository systemNumbersRepository;

    public UPCADecoder(SystemNumbersRepository systemNumbersRepository) {
        this.systemNumbersRepository = systemNumbersRepository;
    }

    @Override
    public UPCAResponse decode(String barcode) {
        var systemNumber = barcode.substring(0, 1);
        var manufacturer = barcode.substring(1, 6);
        var product = barcode.substring(6, 11);
        var checker = barcode.substring(11);

        var systemNumberOpt = systemNumbersRepository.findFirstByNumber(Integer.parseInt(systemNumber));
        if (systemNumberOpt.isEmpty()) {
            throw new BarcodeNotFoundException("System number not found for code: " + systemNumber);
        }

        return UPCAResponse.builder()
                .barcode(barcode)
                .valid(true)
                .systemNumberInfo(systemNumber + " - " + systemNumberOpt.get().getDescription())
                .manufacturer(manufacturer)
                .product(product)
                .checkDigit(checker)
                .build();
    }

}
