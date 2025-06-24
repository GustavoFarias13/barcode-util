package com.gustavofarias.barcodedecoder.dto;

import com.gustavofarias.barcodedecoder.model.BarcodeType;

/**
 * A marker interface for barcode decoding responses.
 * <p>
 * This interface allows different types of barcode response DTOs
 * (like EAN-13, GS1-128, etc.) to be handled generically by the application.
 */
public interface BarcodeDecodedResponse {

    /**
     * Returns the type of barcode encoding detected.
     *
     * @return the barcode encoding type (e.g., EAN-13, GS1-128)
     */
    BarcodeType encodingType();
}
