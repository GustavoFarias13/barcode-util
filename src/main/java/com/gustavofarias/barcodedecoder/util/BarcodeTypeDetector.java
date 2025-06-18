package com.gustavofarias.barcodedecoder.util;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public class BarcodeTypeDetector {

    public static BarcodeType detectType(String barcode) {
        if (barcode == null || !barcode.matches("\\d+")) {
            return BarcodeType.UNKNOWN;
        }

        var length = barcode.length();

        return switch (length) {
            case 13 -> BarcodeType.EAN13;
            case 12 -> BarcodeType.UPCA;
            case 8 -> BarcodeType.EAN8;
            case 14 -> BarcodeType.DUN14;
            default -> BarcodeType.UNKNOWN;
        };
    }
}
