package com.gustavofarias.barcodedecoder.decoder;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BarcodeDecoderFactory {

    private final EAN13Decoder ean13Decoder;
    private final UPCADecoder upcaDecoder;
    private final EAN8Decoder ean8Decoder;

    public BarcodeDecoderFactory(EAN13Decoder ean13Decoder,
                                 UPCADecoder upcaDecoder,
                                 EAN8Decoder ean8Decoder
    ) {
        this.ean13Decoder = ean13Decoder;
        this.upcaDecoder = upcaDecoder;
        this.ean8Decoder = ean8Decoder;
    }

    public Optional<BarcodeDecoder> getDecoder(String barcode) {
        if (barcode == null || barcode.isBlank()) {
            return Optional.empty();
        }

        var length = barcode.length();

        return switch (length) {
            case 13 -> Optional.of(ean13Decoder);
            case 12 -> Optional.of(upcaDecoder);
            case 8  -> Optional.of(ean8Decoder);
            default -> Optional.empty();
        };

    }
}
