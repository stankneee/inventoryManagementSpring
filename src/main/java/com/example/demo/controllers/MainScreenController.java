package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainScreenController {

    private PartService partService;
    private ProductService productService;

    public MainScreenController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword) {
        List<Part> partList = partService.listAll(partkeyword);
        theModel.addAttribute("partkeyword", partkeyword);

        List<Product> productList = productService.listAll(productkeyword);
        theModel.addAttribute("productkeyword", productkeyword);

        if (partList.isEmpty()) {
            // Add sample parts to the partList

            Part part1 = new Part(123, "Pommel", 50.00, 15, 5, 25);
            Part part2 = new Part(456, "Switch", 25.00, 15, 5, 25);
            Part part3 = new Part(789, "Chassis", 100.00, 15, 5, 25);
            Part part4 = new Part(987, "Emitter", 75.00, 15, 5, 25);
            Part part5 = new Part(654, "Kyber Crystal", 500.00, 15, 5, 25);
            Set<String> partNames = new HashSet<>();
            partService.save(part1);
            partService.save(part2);
            partService.save(part3);
            partService.save(part4);
            partService.save(part5);
        }

        // Add sample products to the productList

        if (productList.isEmpty()) {
            Product product1 = new Product("Training Saber", 600.00, 10);
            Product product2 = new Product("Basic Saber", 700.00, 5);
            Product product3 = new Product("Knight Saber", 800.00, 3);
            Product product4 = new Product("Acolyte Saber", 900.00, 8);
            Product product5 = new Product("Dark Saber", 1000.00, 10);
            Set<String> productNames = new HashSet<>(); // Create the productNames set
            productService.save(product1);
            productService.save(product2);
            productService.save(product3);
            productService.save(product4);
            productService.save(product5);


            // Check for duplicate products
            for (Product product : productList) {
                productNames.add(product.getName());
            }

            if (!productNames.contains(product1.getName())) {
                productService.save(product1);
            } else {
                // Create "multi-pack" part
                Part multiPackPart = new Part(111, "Multi-Pack Part", 250.00, 5, 5, 25);
                partService.save(multiPackPart);
            }

            if (!productNames.contains(product2.getName())) {
                productService.save(product2);
            } else {
                // Create "multi-pack" part
                Part multiPackPart = new Part("Multi-Pack Part", 250.00, 5, 5, 25);
                partService.save(multiPackPart);
            }

            if (!productNames.contains(product3.getName())) {
                productService.save(product3);
            } else {
                // Create "multi-pack" part
                Part multiPackPart = new Part(333, "Multi-Pack Part", 250.00, 5, 5, 25);
                partService.save(multiPackPart);
            }

            if (!productNames.contains(product4.getName())) {
                productService.save(product4);
            } else {
                // Create "multi-pack" part
                Part multiPackPart = new Part(444, "Multi-Pack Part", 250.00, 5, 5, 25);
                partService.save(multiPackPart);
            }

            if (!productNames.contains(product5.getName())) {
                productService.save(product5);
            } else {
                // Create "multi-pack" part
                Part multiPackPart = new Part(555, "Multi-Pack Part", 250.00, 5, 5, 25);
                partService.save(multiPackPart);
            }

        }
        theModel.addAttribute("parts", partList);
        theModel.addAttribute("products", productList);

        return "mainscreen";
    }
}
