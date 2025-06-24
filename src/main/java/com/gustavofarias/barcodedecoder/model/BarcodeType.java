package com.gustavofarias.barcodedecoder.model;

/**
 * Enumeration representing the supported types of barcodes.
 * <p>
 * Each enum constant corresponds to a specific barcode standard,
 * identified by its common name.
 */
public enum BarcodeType {
    EAN13("EAN-13"),
    EAN8("EAN-8"),
    UPCA("UPC-A"),
    DUN14("DUN-14"),
    UPCE("UPC-E"),
    GS1128("GS1-128"),
    CODE128("CODE 128"),
    UNKNOWN("Unknown");

    BarcodeType(String name) {
        // Constructor currently does nothing but can be used to store/display the name.
    }
}
