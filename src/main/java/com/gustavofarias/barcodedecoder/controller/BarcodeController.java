package com.gustavofarias.barcodedecoder.controller;

import com.gustavofarias.barcodedecoder.dto.BarcodeResponse;
import com.gustavofarias.barcodedecoder.service.BarcodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/barcode")
public class BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<BarcodeResponse> decode(@PathVariable String barcode) {
        var result = barcodeService.decodeBarcode(barcode);
        return ResponseEntity.ok(result);
    }
}

