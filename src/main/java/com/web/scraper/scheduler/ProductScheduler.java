package com.web.scraper.scheduler;

import com.web.scraper.scraper.ProductScraper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductScheduler {

    private final ProductScraper productScraper;

    public ProductScheduler(ProductScraper productScraper) {
        this.productScraper = productScraper;
    }

    @Scheduled(cron = "0 0 12-18 * * *") // fixedRate = 300000000 for testing
    public void runScraper() {

        System.out.println("Running scraper...");

        productScraper.scrapeProducts();
    }
}