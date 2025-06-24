package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.Code128Response;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import org.springframework.stereotype.Component;

/**
 * Strategy implementation for decoding CODE 128 barcodes.
 * <p>
 * Validates and decodes barcodes of type CODE 128.
 */
@Component
public class Code128Strategy implements BarcodeStrategy {

    /**
     * Returns the barcode type handled by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.CODE128;
    }

    /**
     * Validates if the given barcode is valid for CODE 128.
     * <p>
     * This simple validation checks that the barcode
     * contains only printable ASCII characters (space to tilde).
     *
     * @param barcode the barcode string to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.matches("[\\x20-\\x7E]+");
    }

    /**
     * Decodes the CODE 128 barcode into a response DTO.
     * <p>
     * Here, decoding simply includes the barcode string and its length.
     *
     * @param barcode the barcode string to decode
     * @return a Code128Response containing the decoded data
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        return new Code128Response(
                getCodificationType(),
                barcode,
                barcode.length()
        );
    }
}
