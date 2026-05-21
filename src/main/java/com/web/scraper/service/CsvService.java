package com.web.scraper.service;

import com.web.scraper.model.PdfReceipt;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class CsvService {

    public String generateCsv(List<PdfReceipt> items) {

        StringWriter writer = new StringWriter();

        CSVWriter csvWriter = new CSVWriter(writer);

        csvWriter.writeNext(new String[]{
                "Product Code",
                "Product Name",
                "Unit Price",
                "Currency",
                "Quantity"
        });

        for (PdfReceipt item : items) {

            csvWriter.writeNext(new String[]{
                    item.getProductCode(),
                    item.getProductName(),
                    item.getUnitPrice(),
                    item.getCurrency(),
                    item.getQuantity()
            });
        }

        return writer.toString();
    }
}