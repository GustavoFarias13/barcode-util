package com.gustavofarias.barcodedecoder.dto.gs1;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.model.BarcodeType;

import java.util.List;

public record GS1128Response(
        BarcodeType encodingType,
        String barcode,
        List<GS1Fields> fields
) implements BarcodeDecodedResponse {}

