package com.gustavofarias.barcodeutil.strategy;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.dto.EAN8Response;
import com.gustavofarias.barcodeutil.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodeutil.model.BarcodeType;
import com.gustavofarias.barcodeutil.repository.PrefixRepository;
import org.springframework.stereotype.Component;

/**
 * Strategy implementation for decoding EAN-8 barcodes.
 * <p>
 * Validates and parses the barcode according to EAN-8 format,
 * including looking up prefix information from the database.
 */
@Component
public class EAN8Strategy implements BarcodeStrategy {

    private final PrefixRepository prefixRepository;

    /**
     * Constructor injecting the PrefixRepository dependency
     * for prefix code lookups.
     */
    public EAN8Strategy(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    /**
     * Returns the barcode type handled by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.EAN8;
    }

    /**
     * Validates that the barcode is exactly 8 digits.
     *
     * @param barcode the barcode string to validate
     * @return true if barcode matches 8 digits, false otherwise
     */
    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.matches("\\d{8}");
    }

    /**
     * Decodes the EAN-8 barcode into its components:
     * - prefix (first 3 digits)
     * - product code (next 4 digits)
     * - check digit (last digit)
     * <p>
     * Also looks up prefix information (country) from the repository.
     *
     * @param barcode the 8-digit barcode string
     * @return an EAN8Response DTO with detailed decoded data
     * @throws BarcodeNotFoundException if prefix is not found in DB
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var prefix = Integer.parseInt(barcode.substring(0, 3));
        var product = barcode.substring(3, 7);
        var checkDigit = barcode.substring(7);

        var prefixOpt = prefixRepository.findFirstByCode(prefix)
                .orElseThrow(() -> new BarcodeNotFoundException("Prefix not found: " + prefix));
        var prefixInfo = prefix + " - " + prefixOpt.getCountry();

        return new EAN8Response(
                getCodificationType(),
                barcode,
                prefixInfo,
                product,
                checkDigit
        );
    }
}
