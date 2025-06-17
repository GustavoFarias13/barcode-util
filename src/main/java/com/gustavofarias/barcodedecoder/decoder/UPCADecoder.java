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
        var systemNumberStr = barcode.substring(0, 1);
        var manufacturer = barcode.substring(1, 6);
        var product = barcode.substring(6, 11);
        var checker = barcode.substring(11);

        var systemNumberOpt = systemNumbersRepository.findFirstByNumber(Integer.parseInt(systemNumberStr));
        if (systemNumberOpt.isEmpty()) {
            throw new BarcodeNotFoundException("System number not found for code: " + systemNumberStr);
        }

        return UPCAResponse.builder()
                .barcode(barcode)
                .valid(true)
                .systemNumber(systemNumberStr)
                .systemDescription(systemNumberOpt.get().getDescription())
                .manufacturerCode(manufacturer)
                .productCode(product)
                .checkDigit(checker)
                .build();
    }


}
