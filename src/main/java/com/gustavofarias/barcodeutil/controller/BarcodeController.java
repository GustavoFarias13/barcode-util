package com.gustavofarias.barcodeutil.controller;

import com.gustavofarias.barcodeutil.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodeutil.dto.BarcodeGeneratedResponse;
import com.gustavofarias.barcodeutil.service.BarcodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling barcode requests.
 */
@RestController
@RequestMapping("/api/barcode")
public class BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    /**
     * HTTP GET endpoint to decode a barcode.
     * Example request: /api/barcode/decode?barcode=1234567890128
     *
     * @param barcode the barcode string to be decoded
     * @return a ResponseEntity containing the decoded barcode data
     */
    @GetMapping("/decode")
    public ResponseEntity<BarcodeDecodedResponse> decode(@RequestParam String barcode) {
        var response = barcodeService.decodeBarcode(barcode);
        return ResponseEntity.ok(response);
    }

    /**
     * HTTP GET endpoint to generate a barcode image.
     * Example request: /api/barcode/generate?barcode=1234567890128
     *
     * @param barcode the barcode string to be encoded and generated as an image
     * @return a ResponseEntity containing the generated barcode data and image
     */
    @GetMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BarcodeGeneratedResponse> generateBarcode(@RequestParam String barcode) {
        var response = barcodeService.generateBarcode(barcode);
        return ResponseEntity.ok(response);
    }

}
