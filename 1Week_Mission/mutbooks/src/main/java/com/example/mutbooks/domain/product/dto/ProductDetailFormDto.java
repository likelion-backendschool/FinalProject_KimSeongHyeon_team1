package com.example.mutbooks.domain.product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailFormDto {
    private String name;
    private String imgUrl;
    private String description;
    private int price;
    private int count;
}
