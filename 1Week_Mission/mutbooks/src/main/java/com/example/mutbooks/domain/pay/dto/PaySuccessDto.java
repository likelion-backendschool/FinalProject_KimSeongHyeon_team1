package com.example.mutbooks.domain.pay.dto;

import com.example.mutbooks.domain.cart.dto.CartDto;
import com.example.mutbooks.domain.order.entity.enumulation.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaySuccessDto {
    private String username;
    private Long orderId;
    private List<CartDto> orderHistory;
    private OrderStatus orderStatus;
    private int orderTotalPrice;
}
