package com.gustavofarias.barcodedecoder.decoder;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;

public interface BarcodeDecoder {
    BarcodeDecodedResponse decode(String barcode);
}
