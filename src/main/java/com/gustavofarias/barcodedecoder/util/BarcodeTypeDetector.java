package com.gustavofarias.barcodedecoder.util;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public class BarcodeTypeDetector {

    public static BarcodeType detectType(String code) {
        if (code == null || !code.matches("\\d+")) {
            return BarcodeType.UNKNOWN;
        }

        int length = code.length();

        return switch (length) {
            case 13 -> BarcodeType.EAN_13;
            case 12 -> BarcodeType.UPC_A;
            case 8 -> BarcodeType.EAN_8;
            case 14 -> BarcodeType.DUN_14;
            default -> BarcodeType.UNKNOWN;
        };
    }
}
