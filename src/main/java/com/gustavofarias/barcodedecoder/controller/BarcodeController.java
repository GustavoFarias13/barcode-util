package com.gustavofarias.barcodedecoder.controller;

import com.gustavofarias.barcodedecoder.service.BarcodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/barcode")
public class BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping("/{code}")
    public Map<String, Object> decode(@PathVariable String code) {
        return barcodeService.decodeBarcode(code);
    }
}
