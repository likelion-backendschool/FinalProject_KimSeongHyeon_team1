package com.example.mutbooks.domain.cart.dto;

import com.example.mutbooks.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    private Product product;
    private int count;
}
