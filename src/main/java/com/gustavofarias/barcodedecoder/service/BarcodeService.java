package com.gustavofarias.barcodedecoder.service;

import com.gustavofarias.barcodedecoder.dto.BarcodeResponse;
import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.exception.BarcodeParsingException;
import com.gustavofarias.barcodedecoder.exception.DatabaseAccessException;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;
import com.gustavofarias.barcodedecoder.repository.PrefixRepository;
import com.gustavofarias.barcodedecoder.utils.EAN13Validator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BarcodeService {

    private final PrefixRepository prefixRepository;

    public BarcodeService(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    public BarcodeResponse decodeBarcode(String barcode) {

        if (!EAN13Validator.validate(barcode)) {
            throw new InvalidBarcodeException("Invalid barcode.");
        }

        try {
            var prefixStr = barcode.substring(0, 3);
            var manufacturer = barcode.substring(3, 7);
            var product = barcode.substring(7, 12);
            var checker = barcode.substring(12);

            var prefixOpt = prefixRepository.findFirstByCode(Integer.parseInt(prefixStr));
            if (prefixOpt.isEmpty()) {
                throw new BarcodeNotFoundException("Prefix not found for code: " + prefixStr);
            }

            var country = prefixOpt.get().getCountry();

            return BarcodeResponse.builder()
                    .barcode(barcode)
                    .valid(true)
                    .prefix(prefixStr)
                    .country(country)
                    .manufacturerCode(manufacturer)
                    .productCode(product)
                    .checkDigit(checker)
                    .build();

        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database", e);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new BarcodeParsingException("Error processing barcode parts", e);
        }
    }
}

