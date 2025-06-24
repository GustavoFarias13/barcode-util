package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.model.BarcodeType;

/**
 * Strategy interface for barcode decoding.
 * <p>
 * Implementations provide logic specific to each barcode type,
 * including validation and decoding.
 */
public interface BarcodeStrategy {

    /**
     * Returns the barcode type that this strategy supports.
     *
     * @return the BarcodeType enum representing the barcode standard
     */
    BarcodeType getCodificationType();

    /**
     * Validates if the provided barcode string is valid for this barcode type.
     *
     * @param code the barcode string to validate
     * @return true if valid, false otherwise
     */
    boolean isValid(String code);

    /**
     * Decodes the given barcode string into a structured response.
     * <p>
     * Assumes the barcode is valid (should be checked before calling).
     *
     * @param code the barcode string to decode
     * @return a BarcodeDecodedResponse containing decoded barcode data
     */
    BarcodeDecodedResponse decode(String code);
}
