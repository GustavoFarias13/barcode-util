package com.gustavofarias.barcodeutil.strategy;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.dto.UPCEResponse;
import com.gustavofarias.barcodeutil.exception.BarcodeNotFoundException;
import com.gustavofarias.barcodeutil.model.BarcodeType;
import com.gustavofarias.barcodeutil.repository.SystemNumbersRepository;
import org.springframework.stereotype.Component;

/**
 * Strategy implementation for decoding UPC-E barcodes.
 * <p>
 * Validates and parses the barcode according to UPC-E format,
 * including looking up system number information from the database.
 */
@Component
public class UPCEStrategy implements BarcodeStrategy {

    private final SystemNumbersRepository systemNumbersRepository;

    /**
     * Constructor injecting the SystemNumbersRepository dependency
     * for system number lookups.
     */
    public UPCEStrategy(SystemNumbersRepository systemNumbersRepository) {
        this.systemNumbersRepository = systemNumbersRepository;
    }

    /**
     * Returns the barcode type handled by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.UPCE;
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
     * Decodes the UPC-E barcode into its components:
     * - system number (first digit)
     * - compressed body (next 6 digits)
     * - check digit (last digit)
     * <p>
     * Also looks up system number description from the repository.
     *
     * @param barcode the 8-digit barcode string
     * @return a UPCEResponse DTO with detailed decoded data
     * @throws BarcodeNotFoundException if system number is not found in DB
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        var systemNumber = Integer.parseInt(barcode.substring(0, 1));
        var compressedBody = barcode.substring(1, 7);
        var checkDigit = barcode.substring(7);

        var systemNumberOpt = systemNumbersRepository.findFirstByNumber(systemNumber)
                .orElseThrow(() -> new BarcodeNotFoundException("System number not found for code: " + systemNumber));
        var systemNumberInfo = systemNumber + " - " + systemNumberOpt.getDescription();

        return new UPCEResponse(
                getCodificationType(),
                barcode,
                systemNumberInfo,
                compressedBody,
                checkDigit
        );
    }
}
