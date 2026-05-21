package com.web.scraper.service;

import com.web.scraper.model.PdfReceipt;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PdfService {

    public List<PdfReceipt> extractReceiptItems(MultipartFile file)
            throws IOException {

        List<PdfReceipt> items = new ArrayList<>();

        PDDocument document =
                Loader.loadPDF(file.getBytes());

        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document);

        document.close();

        System.out.println(text);

        Pattern pattern = Pattern.compile(
                "(\\d+\\.\\d+)\\s+" +
                        "(RON)\\s+" +
                        "(-?\\d+)\\s+" +
                        "-?\\d+\\s+" +
                        "H\\d+\\s+" +
                        "\\d+\\s+" +
                        "-?\\d+\\.\\d+" +
                        "([A-Z0-9]+)\\s+" +
                        "([A-Z ]+)"
        );

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            PdfReceipt item = new PdfReceipt();

            item.setUnitPrice(matcher.group(1));

            item.setCurrency(matcher.group(2));

            item.setQuantity(matcher.group(3));

            item.setProductCode(matcher.group(4));

            item.setProductName(matcher.group(5).trim()
            );

            items.add(item);
        }

        return items;
    }
}