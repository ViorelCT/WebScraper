package com.web.scraper.service;

import com.web.scraper.model.Product;
import com.web.scraper.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    private final CurrencyService currencyService;

    public ProductService(ProductRepository repository, CurrencyService currencyService) {

        this.repository = repository;
        this.currencyService = currencyService;
    }

    public void saveProduct(Product product) {

        Optional<Product> existing =
                repository.findByName(product.getName());

        if (existing.isEmpty()) {

            double exchangeRate = currencyService.getEurToRonRate();

            product.setExchangeRate(exchangeRate);

            double price = Double.parseDouble(product.getPrice());

            product.setPriceRon(price * exchangeRate);

            repository.save(product);

            System.out.println("Saved: " + product.getName());

        } else {

            System.out.println("Skipped duplicate: " + product.getName());
        }
    }
}