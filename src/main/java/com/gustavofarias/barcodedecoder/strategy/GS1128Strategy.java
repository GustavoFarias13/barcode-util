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

@Component
public class GS1128Strategy implements BarcodeStrategy {

    private final ApplicationIdentifiersRepository aiRepository;

    public GS1128Strategy(ApplicationIdentifiersRepository aiRepository) {
        this.aiRepository = aiRepository;
    }

    @Override
    public BarcodeType getCodificationType() {
        return BarcodeType.GS1128;
    }

    @Override
    public boolean isValid(String barcode) {
        return barcode != null && barcode.startsWith("("); // Simples validação estrutural
    }

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
                index = closing + 1;
                continue;
            }

            var description = aiRepository.findFirstByCode(aiCode)
                    .map(ApplicationIdentifiers::getDescription)
                    .orElse("Unknown AI");

            index = closing + 1;
            var value = extractValue(barcode, index);
            fields.add(new GS1Fields(aiCodeStr, description, value));

            index += value.length(); // avança para o próximo '('
        }

        return new GS1128Response(getCodificationType(), barcode, fields);
    }

    private String extractAiCode(String barcode, int start, int end) {
        return barcode.substring(start + 1, end);
    }

    private Integer parseAiCode(String aiCodeStr) {
        try {
            return Integer.parseInt(aiCodeStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String extractValue(String barcode, int start) {
        var end = start;
        while (end < barcode.length() && barcode.charAt(end) != '(') {
            end++;
        }
        return barcode.substring(start, end);
    }

}
