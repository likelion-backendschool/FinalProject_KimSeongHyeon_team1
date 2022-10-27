package com.example.mutbooks.domain.cart.dto;

import com.example.mutbooks.domain.order.entity.enumulation.OrderType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartListDto {
    private List<CartDto> cartDtos;
    private OrderType orderType;
    private int totalPrice;
}
