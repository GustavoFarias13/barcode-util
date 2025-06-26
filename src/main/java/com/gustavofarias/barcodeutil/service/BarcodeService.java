package com.gustavofarias.barcodeutil.service;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.dto.BarcodeGeneratedResponse;
import com.gustavofarias.barcodeutil.exception.BarcodeNotGeneratedException;
import com.gustavofarias.barcodeutil.exception.InvalidBarcodeException;
import com.gustavofarias.barcodeutil.strategy.BarcodeStrategy;
import com.gustavofarias.barcodeutil.strategy.BarcodeStrategyFactory;
import com.gustavofarias.barcodeutil.util.BarcodeImageGenerator;
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

    /**
     * Generates a barcode image for the given barcode string.
     * <p>
     * The method selects the appropriate barcode generation strategy based on the barcode format,
     * validates the barcode, and then generates a Base64 encoded image.
     *
     * @param barcode the barcode string to be encoded and generated as an image
     * @return a {@link BarcodeGeneratedResponse} containing the barcode type, original barcode,
     *         and the Base64 encoded barcode image
     * @throws InvalidBarcodeException       if the barcode is invalid for the detected type
     * @throws BarcodeNotGeneratedException  if barcode image generation fails
     */
    public BarcodeGeneratedResponse generateBarcode(String barcode) {
        BarcodeStrategy strategy = strategyFactory.getStrategy(barcode);

        if (!strategy.isValid(barcode)) {
            throw new InvalidBarcodeException("Invalid barcode for type: " + strategy.getCodificationType());
        }

        try {
            var type = strategy.getCodificationType();
            var base64 = BarcodeImageGenerator.generateBase64Image(barcode, type);

            return new BarcodeGeneratedResponse(type, barcode, base64);
        } catch (Exception e) {
            throw new BarcodeNotGeneratedException("Failed to generate barcode image: " + e);
        }
    }

}
