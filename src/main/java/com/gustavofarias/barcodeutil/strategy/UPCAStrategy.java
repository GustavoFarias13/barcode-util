package com.gustavofarias.barcodeutil.strategy;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.dto.UPCAResponse;
import com.gustavofarias.barcodeutil.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodeutil.model.BarcodeType;
import com.gustavofarias.barcodeutil.repository.SystemNumbersRepository;
import org.springframework.stereotype.Component;

/**
 * Strategy implementation for decoding UPC-A barcodes.
 * <p>
 * Validates and parses the barcode according to UPC-A format,
 * including looking up system number information from the database.
 */
@Component
public class UPCAStrategy implements BarcodeStrategy {

    private final SystemNumbersRepository systemNumbersRepository;

    /**
     * Constructor injecting the SystemNumbersRepository dependency
     * for system number lookups.
     */
    public UPCAStrategy(SystemNumbersRepository systemNumbersRepository) {
        this.systemNumbersRepository = systemNumbersRepository;
    }

    /**
     * Returns the barcode type handled by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.UPCA;
    }

    /**
     * Validates that the barcode is exactly 12 digits.
     *
     * @param barcode the barcode string to validate
     * @return true if barcode matches 12 digits, false otherwise
     */
    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.matches("\\d{12}");
    }

    /**
     * Decodes the UPC-A barcode into its components:
     * - system number (first digit)
     * - manufacturer code (next 5 digits)
     * - product code (next 5 digits)
     * - check digit (last digit)
     * <p>
     * Also looks up system number description from the repository.
     *
     * @param barcode the 12-digit barcode string
     * @return a UPCAResponse DTO with detailed decoded data
     * @throws BarcodeNotFoundException if system number is not found in DB
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var systemNumber = Integer.parseInt(barcode.substring(0, 1));
        var manufacturer = barcode.substring(1, 6);
        var product = barcode.substring(6, 11);
        var checkDigit = barcode.substring(11);

        var systemNumberOpt = systemNumbersRepository.findFirstByNumber(systemNumber)
                .orElseThrow(() -> new BarcodeNotFoundException("System number not found for code: " + systemNumber));
        var systemNumberInfo = systemNumber + " - " + systemNumberOpt.getDescription();

        return new UPCAResponse(
                getCodificationType(),
                barcode,
                systemNumberInfo,
                manufacturer,
                product,
                checkDigit
        );
    }
}
