package com.gustavofarias.barcodeutil.strategy;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.dto.EAN13Response;
import com.gustavofarias.barcodeutil.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodeutil.model.BarcodeType;
import com.gustavofarias.barcodeutil.repository.PrefixRepository;
import org.springframework.stereotype.Component;

/**
 * Strategy implementation for decoding EAN-13 barcodes.
 * <p>
 * Validates and parses the barcode according to EAN-13 format,
 * including looking up prefix information from the database.
 */
@Component
public class EAN13Strategy implements BarcodeStrategy {

    private final PrefixRepository prefixRepository;

    /**
     * Constructor injecting the PrefixRepository dependency
     * for prefix code lookups.
     */
    public EAN13Strategy(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    /**
     * Returns the barcode type handled by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.EAN13;
    }

    /**
     * Validates that the barcode is exactly 13 digits.
     *
     * @param barcode the barcode string to validate
     * @return true if barcode matches 13 digits, false otherwise
     */
    @Override
    public Boolean isValid(String barcode) {
        return barcode != null && barcode.matches("\\d{13}");
    }

    /**
     * Decodes the EAN-13 barcode into its components:
     * - prefix (first 3 digits)
     * - manufacturer code (next 4 digits)
     * - product code (next 5 digits)
     * - check digit (last digit)
     * <p>
     * Also looks up prefix information (country) from the repository.
     *
     * @param barcode the 13-digit barcode string
     * @return an EAN13Response DTO with detailed decoded data
     * @throws BarcodeNotFoundException if prefix is not found in DB
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var prefix = Integer.parseInt(barcode.substring(0, 3));
        var manufacturer = barcode.substring(3, 7);
        var product = barcode.substring(7, 12);
        var checkDigit = barcode.substring(12);

        var prefixOpt = prefixRepository.findFirstByCode(prefix)
                .orElseThrow(() -> new BarcodeNotFoundException("Prefix not found: " + prefix));
        var prefixInfo = prefix + " - " + prefixOpt.getCountry();

        return new EAN13Response(
                getCodificationType(),
                barcode,
                prefixInfo,
                manufacturer,
                product,
                checkDigit
        );
    }
}
