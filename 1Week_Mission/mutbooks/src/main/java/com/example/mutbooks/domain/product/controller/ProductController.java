package com.example.mutbooks.domain.product.controller;

import com.example.mutbooks.domain.product.entity.Product;
import com.example.mutbooks.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /*기본창*/
    @GetMapping("/product/list")
    public String showProductView(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);

        return "product/product_list";
    }
}
