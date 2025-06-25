package com.gustavofarias.barcodeutil.dto.gs1;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.model.BarcodeType;

import java.util.List;

/**
 * A response DTO that represents a decoded GS1-128 barcode.
 * This is used to return structured data extracted from a GS1-128 string.
 *
 * This class implements BarcodeDecodedResponse, making it compatible with the general
 * response interface used by the decoding service/controller.
 *
 * @param encodingType The type of barcode encoding (should be GS1-128 in this case)
 * @param barcode      The original raw barcode string
 * @param fields       A list of parsed GS1 fields extracted from the barcode
 */
public record GS1128Response(
        BarcodeType encodingType,
        String barcode,
        List<GS1Fields> fields
) implements BarcodeDecodedResponse {}
