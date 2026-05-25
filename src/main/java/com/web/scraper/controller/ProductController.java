package com.web.scraper.controller;

import com.web.scraper.model.Product;
import com.web.scraper.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort,
            Model model) {

        List<Product> products;

        if (keyword != null && !keyword.isEmpty()) {

            products = repository.findByNameContainingIgnoreCase(keyword);

        } else {

            products = repository.findAll();
        }

        if ("low to high".equals(sort)) {

            products.sort(
                    Comparator.comparing(
                            p -> Double.parseDouble(p.getPrice())
                    )
            );
        }

        if ("high to low".equals(sort)) {

            products.sort(
                    Comparator.comparing(
                            (Product p) ->
                                    Double.parseDouble(p.getPrice())
                    ).reversed()
            );
        }

        model.addAttribute("products", products);

        return "products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

        repository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              Model model) {

        Product product = repository.findById(id)
                .orElseThrow();

        model.addAttribute("product", product);

        return "editproduct";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {

        repository.save(product);

        return "redirect:/";
    }
}