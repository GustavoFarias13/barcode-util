package com.gustavofarias.barcodedecoder.decoder;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BarcodeDecoderFactory {

    private final EAN13Decoder ean13Decoder;
    private final UPCADecoder upcaDecoder;
    private final EAN8Decoder ean8Decoder;
    private final DUN14Decoder dun14Decoder;

    public BarcodeDecoderFactory(EAN13Decoder ean13Decoder,
                                 UPCADecoder upcaDecoder,
                                 EAN8Decoder ean8Decoder,
                                 DUN14Decoder dun14Decoder) {
        this.ean13Decoder = ean13Decoder;
        this.upcaDecoder = upcaDecoder;
        this.ean8Decoder = ean8Decoder;
        this.dun14Decoder = dun14Decoder;
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
            case 14 -> Optional.of(dun14Decoder);
            default -> Optional.empty();
        };

    }
}
