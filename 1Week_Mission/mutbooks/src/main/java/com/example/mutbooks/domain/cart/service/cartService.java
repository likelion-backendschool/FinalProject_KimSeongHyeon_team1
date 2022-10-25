package com.example.mutbooks.domain.cart.service;

import com.example.mutbooks.domain.cart.entity.Cart;
import com.example.mutbooks.domain.cart.repository.CartRepository;
import com.example.mutbooks.domain.product.dto.ProductDetailFormDto;
import com.example.mutbooks.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public Cart save(ProductDetailFormDto productDetailFormDto, String username, Product product) {
        Cart cart = Cart.builder().
                username(username)
                .count(productDetailFormDto.getCount())
                .isOrdered(false)
                .product(product)
                .build();

        return cartRepository.save(cart);
    }
}
