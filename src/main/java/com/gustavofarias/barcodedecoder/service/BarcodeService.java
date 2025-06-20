package com.gustavofarias.barcodedecoder.service;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;
import com.gustavofarias.barcodedecoder.strategy.BarcodeStrategy;
import com.gustavofarias.barcodedecoder.strategy.BarcodeStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class BarcodeService {

    private final BarcodeStrategyFactory strategyFactory;

    public BarcodeService(BarcodeStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public BarcodeDecodedResponse decodeBarcode(String barcode) {
        BarcodeStrategy strategy = strategyFactory.getStrategy(barcode);
        if (!strategy.isValid(barcode)) {
            throw new InvalidBarcodeException("Invalid barcode for type: " + strategy.getCodificationType());
        }

        return strategy.decode(barcode);

    }
}
