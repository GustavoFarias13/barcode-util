package com.gustavofarias.barcodedecoder.util;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

public class BarcodeTypeDetector {

    private static final char FNC1 = 29;

    public static BarcodeType detectType(String barcode) {
        if (barcode == null || barcode.isEmpty()) return BarcodeType.UNKNOWN;

        if (isGs1128(barcode)) {
            return BarcodeType.GS1128;
        }

        if (barcode.matches("\\d+")) {
            return switch (barcode.length()) {
                case 13 -> BarcodeType.EAN13;
                case 12 -> BarcodeType.UPCA;
                case 8 -> detectEan8OrUpce(barcode);
                case 14 -> BarcodeType.DUN14;
                default -> BarcodeType.UNKNOWN;
            };
        }

        if (barcode.matches("[\\x20-\\x7E]+")) {
            return BarcodeType.CODE128;
        }

        return BarcodeType.UNKNOWN;
    }

    public static boolean isGs1128(String barcode) {
        if (barcode == null || barcode.isEmpty()) return false;

        if (barcode.charAt(0) == FNC1) {
            return true;
        }

        return barcode.matches("^\\(\\d{2,3}\\).+");
    }

    /**
     * Diferencia se o código de 8 dígitos é UPC-E ou EAN-8
     */
    private static BarcodeType detectEan8OrUpce(String barcode) {
        var systemDigit = barcode.charAt(0);

        if (systemDigit == '0' || systemDigit == '1') {
            var expanded = expandUpce(barcode);
            if (expanded != null) {
                return BarcodeType.UPCE;
            }
        }

        return BarcodeType.EAN8;
    }

    /**
     * Expande UPC-E para UPC-A, retorna null se inválido
     */
    private static String expandUpce(String upce) {
        if (!upce.matches("\\d{8}")) return null;

        var systemDigit = upce.charAt(0);
        var manufacturer = upce.substring(1, 6);
        var lastDigit = upce.charAt(6);
        var checkDigit = upce.charAt(7);

        var upcA = new StringBuilder();

        upcA.append(systemDigit);

        switch (lastDigit) {
            case '0', '1', '2' -> upcA.append(manufacturer, 0, 2)
                    .append(lastDigit)
                    .append("0000")
                    .append(manufacturer, 2, 5);
            case '3' -> upcA.append(manufacturer, 0, 3)
                    .append("00000")
                    .append(manufacturer, 3, 5);
            case '4' -> upcA.append(manufacturer, 0, 4)
                    .append("00000")
                    .append(manufacturer.charAt(4));
            case '5', '6', '7', '8', '9' -> upcA.append(manufacturer)
                    .append("0000")
                    .append(lastDigit);
            default -> {
                return null;
            }
        }

        upcA.append(checkDigit);

        var upcAStr = upcA.toString();

        if (isValidCheckDigit(upcAStr)) {
            return upcAStr;
        } else {
            return null;
        }
    }

    /**
     * Valida o dígito verificador do código UPC-A ou EAN-13 (módulo 10)
     */
    private static boolean isValidCheckDigit(String barcode) {
        if (barcode == null || !barcode.matches("\\d+")) return false;

        var length = barcode.length();
        if (length != 12 && length != 13) return false;

        var sum = 0;
        var odd = true;

        for (int i = length - 2; i >= 0; i--) {
            var digit = barcode.charAt(i) - '0';
            sum += odd ? digit * 3 : digit;
            odd = !odd;
        }

        var expectedCheckDigit = (10 - (sum % 10)) % 10;
        var actualCheckDigit = barcode.charAt(length - 1) - '0';

        return expectedCheckDigit == actualCheckDigit;
    }

}
