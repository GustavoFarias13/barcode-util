package com.gustavofarias.barcodeutil.strategy;

import com.gustavofarias.barcodeutil.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodeutil.model.BarcodeType;
import com.gustavofarias.barcodeutil.util.BarcodeTypeDetector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Factory class to provide the appropriate BarcodeStrategy
 * implementation based on the detected barcode type.
 * <p>
 * It collects all available strategies and maps them by their BarcodeType.
 */
@Component
public class BarcodeStrategyFactory {

    private final Map<BarcodeType, BarcodeStrategy> strategies;

    /**
     * Constructor that initializes the strategy map by
     * collecting all beans implementing BarcodeStrategy.
     *
     * @param strategyList the list of available BarcodeStrategy implementations
     */
    public BarcodeStrategyFactory(List<BarcodeStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(BarcodeStrategy::getCodificationType, s -> s));
    }

    /**
     * Returns the BarcodeStrategy corresponding to the detected type
     * of the given barcode string.
     * <p>
     * Uses BarcodeTypeDetector to identify the barcode type, then
     * looks up the matching strategy.
     *
     * @param barcode the barcode string whose strategy is requested
     * @return the BarcodeStrategy implementation for the barcode's type
     * @throws BarcodeNotFoundException if no suitable strategy is found
     */
    public BarcodeStrategy getStrategy(String barcode) {
        var strategy = strategies.get(BarcodeTypeDetector.detectType(barcode));
        if (strategy == null)
            throw new BarcodeNotFoundException("Unsupported barcode type: " + barcode);
        return strategy;
    }
}
