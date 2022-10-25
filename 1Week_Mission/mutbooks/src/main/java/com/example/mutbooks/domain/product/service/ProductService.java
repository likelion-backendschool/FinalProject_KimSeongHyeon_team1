package com.example.mutbooks.domain.product.service;

import com.example.mutbooks.domain.product.dto.ProductDetailFormDto;
import com.example.mutbooks.domain.product.entity.Product;
import com.example.mutbooks.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> findAll() {
        List<Product> findProduct = productRepository.findAll();
        return findProduct;
    }

    public Product findById(Long id) {
        Optional<Product> findProduct =  productRepository.findById(id);
        return findProduct.get();
    }

    public ProductDetailFormDto getProductFormById(Product product) {
            ProductDetailFormDto productDetailFormDto = ProductDetailFormDto.builder()
                    .name(product.getName())
                    .imgUrl(product.getImgUrl())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();

            return productDetailFormDto;
    }
}
