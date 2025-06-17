package com.gustavofarias.barcodedecoder.service;

import com.gustavofarias.barcodedecoder.decoder.BarcodeDecoderFactory;
import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.exception.BarcodeParsingException;
import com.gustavofarias.barcodedecoder.exception.DatabaseAccessException;
import com.gustavofarias.barcodedecoder.exception.InvalidBarcodeException;
import com.gustavofarias.barcodedecoder.util.BarcodeValidationUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BarcodeService {

    private final BarcodeDecoderFactory decoderFactory;

    public BarcodeService(BarcodeDecoderFactory decoderFactory) {
        this.decoderFactory = decoderFactory;
    }

    public BarcodeDecodedResponse decodeBarcode(String barcode) {
        if (!BarcodeValidationUtil.validateWithAutoDetection(barcode)) {
            throw new InvalidBarcodeException("Invalid barcode.");
        }

        var decoderOpt = decoderFactory.getDecoder(barcode);
        var decoder = decoderOpt.orElseThrow(() ->
                new InvalidBarcodeException("Unsupported barcode type or length"));

        try {
            return decoder.decode(barcode);
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database", e);
        } catch (Exception e) {
            throw new BarcodeParsingException("Error processing barcode", e);
        }
    }
}

