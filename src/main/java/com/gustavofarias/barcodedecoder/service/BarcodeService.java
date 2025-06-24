package com.gustavofarias.barcodedecoder.service;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;
import com.gustavofarias.barcodedecoder.strategy.BarcodeStrategy;
import com.gustavofarias.barcodedecoder.strategy.BarcodeStrategyFactory;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for barcode decoding logic.
 * <p>
 * It delegates decoding to a specific strategy selected
 * based on the barcode format.
 */
@Service
public class BarcodeService {

    private final BarcodeStrategyFactory strategyFactory;

    /**
     * Constructs the service with a strategy factory.
     *
     * @param strategyFactory factory that provides barcode decoding strategies
     */
    public BarcodeService(BarcodeStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * Decodes the given barcode string using the appropriate strategy.
     * <p>
     * First, the suitable strategy is retrieved from the factory.
     * Then, the barcode is validated using the strategy's validation method.
     * If invalid, an exception is thrown.
     * Otherwise, the barcode is decoded and a response object is returned.
     *
     * @param barcode the barcode string to decode
     * @return a BarcodeDecodedResponse representing the decoded barcode data
     * @throws InvalidBarcodeException if the barcode does not validate for the detected type
     */
    public BarcodeDecodedResponse decodeBarcode(String barcode) {
        BarcodeStrategy strategy = strategyFactory.getStrategy(barcode);

        if (!strategy.isValid(barcode)) {
            throw new InvalidBarcodeException("Invalid barcode for type: " + strategy.getCodificationType());
        }

        return strategy.decode(barcode);
    }
}
