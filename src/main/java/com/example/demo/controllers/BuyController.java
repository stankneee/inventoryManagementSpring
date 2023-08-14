package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BuyController {

    private final ProductService productService;

    public BuyController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/buy/{productId}")
    public String buyProduct(@PathVariable("productId") int productId, Model model) {
        Product product = productService.findById(productId);

        if (product != null && product.getInv() > 0) {
            boolean purchaseResult = productService.buyProduct(product);

            if (purchaseResult) {
                model.addAttribute("successMessage", "Purchase successful!");
                return "successfulPurchase";
            } else {
                model.addAttribute("errorMessage", "Purchase failed: Product is out of stock.");
                return "unsuccessfulPurchase";
            }
        } else {
            model.addAttribute("errorMessage", "Purchase failed: Product not found.");
            return "unsuccessfulPurchase";
        }
    }
}
