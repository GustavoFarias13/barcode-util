package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.DUN14Response;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import org.springframework.stereotype.Component;

/**
 * Strategy implementation for decoding DUN-14 barcodes.
 * <p>
 * Validates and decodes barcodes that follow the DUN-14 format,
 * which consists of 14 numeric digits.
 */
@Component
public class DUN14Strategy implements BarcodeStrategy {

    /**
     * Returns the barcode type supported by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.DUN14;
    }

    /**
     * Validates if the barcode is valid for DUN-14:
     * must be exactly 14 digits.
     *
     * @param barcode the barcode string to validate
     * @return true if the barcode matches the DUN-14 numeric format
     */
    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.matches("\\d{14}");
    }

    /**
     * Decodes the DUN-14 barcode into its components:
     * - indicator digit (first digit)
     * - GTIN base (next 12 digits)
     * - check digit (last digit)
     *
     * @param barcode the 14-digit barcode string to decode
     * @return a DUN14Response DTO containing the parsed parts
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var indicatorDigit = String.valueOf(barcode.charAt(0));
        var gtinBase = barcode.substring(1, 13);
        var checkDigit = String.valueOf(barcode.charAt(13));

        return new DUN14Response(
                getCodificationType(),
                barcode,
                indicatorDigit,
                gtinBase,
                checkDigit
        );
    }
}
