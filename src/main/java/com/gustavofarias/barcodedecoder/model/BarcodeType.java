package com.gustavofarias.barcodedecoder.model;

public enum BarcodeType {
    EAN13("EAN-13", true),
    EAN8("EAN-8", true),
    UPCA("UPC-A", true),
    DUN14("DUN-14", false),
    UPCE("UPC-E", true),
    GS1128("GS1-128", false),
    CODE128("CODE 128", false),
    UNKNOWN("Unknown", false);

    private final boolean requiresNormalization;

    BarcodeType(String name, boolean requiresNormalization) {
        this.requiresNormalization = requiresNormalization;
    }

    public boolean requiresNormalization() {
        return requiresNormalization;
    }
    }
