package com.gustavofarias.barcodedecoder.strategy;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.dto.gs1.GS1128Response;
import com.gustavofarias.barcodedecoder.dto.gs1.GS1Fields;
import com.gustavofarias.barcodedecoder.model.ApplicationIdentifiers;
import com.gustavofarias.barcodedecoder.model.BarcodeType;
import com.gustavofarias.barcodedecoder.repository.ApplicationIdentifiersRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy implementation for decoding GS1-128 (GS1128) barcodes.
 * <p>
 * Parses the barcode by extracting Application Identifiers (AIs) and their values.
 * Uses a repository to get descriptions for known AI codes.
 */
@Component
public class GS1128Strategy implements BarcodeStrategy {

    private final ApplicationIdentifiersRepository aiRepository;

    /**
     * Constructor injecting the ApplicationIdentifiersRepository for AI lookups.
     */
    public GS1128Strategy(ApplicationIdentifiersRepository aiRepository) {
        this.aiRepository = aiRepository;
    }

    /**
     * Returns the barcode type handled by this strategy.
     */
    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.GS1128;
    }

    /**
     * Validates the barcode structure.
     * A simple validation checking if it starts with '(',
     * indicating the beginning of an AI code.
     *
     * @param barcode the barcode string to validate
     * @return true if the barcode starts with '(', false otherwise
     */
    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.startsWith("(");
    }

    /**
     * Decodes the GS1-128 barcode by extracting each Application Identifier (AI) and its value.
     * <p>
     * The barcode format is expected to contain multiple segments like:
     * (AI)Value(AI)Value...
     * Each AI is enclosed in parentheses.
     * <p>
     * For each AI found:
     * - Extract AI code and parse it as integer.
     * - Lookup AI description from repository.
     * - Extract the value associated with the AI.
     * <p>
     * Returns a GS1128Response containing all parsed fields.
     *
     * @param barcode the GS1-128 barcode string to decode
     * @return a GS1128Response DTO with all extracted fields
     */
    @Override
    public BarcodeDecodedResponse decode(String barcode) {
        List<GS1Fields> fields = new ArrayList<>();
        var index = 0;

        while (index < barcode.length()) {
            if (barcode.charAt(index) != '(') break;

            var closing = barcode.indexOf(')', index);
            if (closing == -1) break;

            var aiCodeStr = extractAiCode(barcode, index, closing);
            var aiCode = parseAiCode(aiCodeStr);
            if (aiCode == null) {
                index = closing + 1;  // skip invalid AI code
                continue;
            }

            var description = aiRepository.findFirstByCode(aiCode)
                    .map(ApplicationIdentifiers::getDescription)
                    .orElse("Unknown AI");

            index = closing + 1;
            var value = extractValue(barcode, index);
            fields.add(new GS1Fields(aiCodeStr, description, value));

            index += value.length();  // move index after the extracted value
        }

        return new GS1128Response(getCodificationType(), barcode, fields);
    }

    /**
     * Extracts the AI code string from barcode between given indices.
     *
     * @param barcode the barcode string
     * @param start   index of '('
     * @param end     index of ')'
     * @return the AI code string inside parentheses
     */
    private String extractAiCode(String barcode, int start, int end) {
        return barcode.substring(start + 1, end);
    }

    /**
     * Attempts to parse the AI code string into an Integer.
     *
     * @param aiCodeStr the AI code string
     * @return the parsed Integer, or null if parsing fails
     */
    private Integer parseAiCode(String aiCodeStr) {
        try {
            return Integer.parseInt(aiCodeStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Extracts the value following an AI code until the next '(' or end of string.
     *
     * @param barcode the barcode string
     * @param start   starting index after AI closing parenthesis
     * @return the extracted value string
     */
    private String extractValue(String barcode, int start) {
        var end = start;
        while (end < barcode.length() && barcode.charAt(end) != '(') {
            end++;
        }
        return barcode.substring(start, end);
    }

}
