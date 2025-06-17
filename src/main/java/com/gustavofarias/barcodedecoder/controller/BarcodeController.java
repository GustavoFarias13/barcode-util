package com.gustavofarias.barcodedecoder.controller;

import com.gustavofarias.barcodedecoder.dto.BarcodeDecodedResponse;
import com.gustavofarias.barcodedecoder.service.BarcodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/barcode-decoder")
public class BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<BarcodeDecodedResponse> decode(@PathVariable String barcode) {
        var result = barcodeService.decodeBarcode(barcode);
        return ResponseEntity.ok(result);
    }
}

