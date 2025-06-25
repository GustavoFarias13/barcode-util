package com.gustavofarias.barcodeutil.util;

import com.gustavofarias.barcodeutil.model.BarcodeType;

public class BarcodeTypeDetector {

    // ASCII Group Separator character (FNC1 in GS1 barcodes)
    private static final char FNC1 = 29;

    /**
     * Detects the type of barcode based on its format and content.
     *
     * @param barcode The barcode string to detect.
     * @return The detected BarcodeType enum value, or UNKNOWN if not recognized.
     */
    public static BarcodeType detectType(String barcode) {
        if (barcode == null || barcode.isEmpty()) return BarcodeType.UNKNOWN;

        if (isGs1128(barcode)) {
            return BarcodeType.GS1128;
        }

        // Numeric-only barcodes: decide by length
        if (barcode.matches("\\d+")) {
            return switch (barcode.length()) {
                case 13 -> BarcodeType.EAN13;
                case 12 -> BarcodeType.UPCA;
                case 8 -> detectEan8OrUpce(barcode); // Could be EAN-8 or UPC-E
                case 14 -> BarcodeType.DUN14;
                default -> BarcodeType.UNKNOWN;
            };
        }

        // For printable ASCII codes, assume Code128
        if (barcode.matches("[\\x20-\\x7E]+")) {
            return BarcodeType.CODE128;
        }

        return BarcodeType.UNKNOWN;
    }

    /**
     * Checks if the barcode is a GS1-128 code.
     * GS1-128 barcodes start with the FNC1 character or '(' followed by 2-3 digits.
     *
     * @param barcode The barcode string.
     * @return true if it matches GS1-128 format, false otherwise.
     */
    public static boolean isGs1128(String barcode) {
        if (barcode == null || barcode.isEmpty()) return false;

        // Check if first character is FNC1 (ASCII 29)
        if (barcode.charAt(0) == FNC1) {
            return true;
        }

        // Or starts with AI pattern like "(01)", "(10)", etc.
        return barcode.matches("^\\(\\d{2,3}\\).+");
    }

    /**
     * Determines whether an 8-digit code is an EAN-8 or UPC-E.
     * UPC-E codes usually start with 0 or 1.
     *
     * @param barcode The 8-digit barcode string.
     * @return BarcodeType.UPCE if valid UPC-E, otherwise BarcodeType.EAN8.
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
     * Expands a UPC-E barcode to its equivalent UPC-A form.
     * Returns null if the UPC-E code is invalid.
     *
     * @param upce The UPC-E barcode string (8 digits).
     * @return The expanded UPC-A barcode string or null if invalid.
     */
    private static String expandUpce(String upce) {
        if (!upce.matches("\\d{8}")) return null;

        var systemDigit = upce.charAt(0);
        var manufacturer = upce.substring(1, 6);
        var lastDigit = upce.charAt(6);
        var checkDigit = upce.charAt(7);

        var upcA = new StringBuilder();

        upcA.append(systemDigit);

        // Expansion rules depending on last digit of manufacturer code
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

        // Validate the check digit of the expanded UPC-A barcode
        if (isValidCheckDigit(upcAStr)) {
            return upcAStr;
        } else {
            return null;
        }
    }

    /**
     * Validates the check digit of UPC-A or EAN-13 barcode using modulo 10 algorithm.
     *
     * @param barcode The barcode string (must be 12 or 13 digits).
     * @return true if check digit is valid, false otherwise.
     */
    private static boolean isValidCheckDigit(String barcode) {
        if (barcode == null || !barcode.matches("\\d+")) return false;

        var length = barcode.length();
        if (length != 12 && length != 13) return false;

        var sum = 0;
        var odd = true;

        // Calculate weighted sum of digits (right to left, excluding check digit)
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
