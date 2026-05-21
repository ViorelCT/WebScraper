package com.web.scraper.controller;

import com.web.scraper.model.PdfReceipt;
import com.web.scraper.service.CsvService;
import com.web.scraper.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PdfController {

    private final PdfService pdfService;
    private final CsvService csvService;

    public PdfController(PdfService pdfService,
                         CsvService csvService) {

        this.pdfService = pdfService;
        this.csvService = csvService;
    }

    @PostMapping("/upload-pdf")
    public ResponseEntity<String> uploadPdf(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        List<PdfReceipt> items =
                pdfService.extractReceiptItems(file);

        String csvContent =
                csvService.generateCsv(items);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=receipts.csv"
                )
                .contentType(MediaType.TEXT_PLAIN)
                .body(csvContent);
    }
}
