package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.util.BarcodeTypeDetector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BarcodeStrategyFactory {
    private final Map<BarcodeType, BarcodeStrategy> strategies;

    public BarcodeStrategyFactory(List<BarcodeStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(BarcodeStrategy::getCodificationType, s -> s));
    }

    public BarcodeStrategy getStrategy(String type) {
        var strategy = strategies.get(BarcodeTypeDetector.detectType(type));
        if (strategy == null)
            throw new BarcodeNotFoundException("Unsupported barcode type: " + type);
        return strategy;
    }
}
