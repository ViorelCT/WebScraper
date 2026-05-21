package com.web.scraper.scraper;

import com.microsoft.playwright.*;
import com.web.scraper.model.Product;
import com.web.scraper.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductScraper {

    boolean nextPage = true;

    private final ProductService productService;

    public ProductScraper(ProductService productService) {
        this.productService = productService;
    }

    public void scrapeProducts() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
            );

            Page page = browser.newPage();

            page.navigate("https://www.web-scraping.dev/login");

            page.fill("input[name='username']", "user123");

            page.fill("input[name='password']", "password");

            page.click("button:has-text('Submit')");

            page.waitForTimeout(3000);

            page.navigate("https://www.web-scraping.dev/products?category=consumables");


            while (nextPage) {

                Locator products = page.locator(".product");

                int count = products.count();

                for (int i = 0; i < count; i++) {

                    Locator product = products.nth(i);

                    String name = product.locator("h3 a").innerText();

                    String description = product.locator(".short-description").innerText();

                    String price = product.locator(".price").innerText();

                    String imageUrl = product.locator("img").getAttribute("src");

                    Product p = new Product();

                    p.setName(name);
                    p.setDescription(description);
                    p.setPrice(price);
                    p.setImageUrl(imageUrl);

                    productService.saveProduct(p);
                }

                Locator nextButton = page.locator(".paging a:has-text('>')");

                if (nextButton.count() == 0) {

                    nextPage = false;

                } else {

                    String nextUrl = nextButton.first().getAttribute("href");

                    page.navigate(nextUrl);

                    page.waitForTimeout(2000);
                }
            }

            page.waitForTimeout(5000);

            browser.close();
        }
    }
}