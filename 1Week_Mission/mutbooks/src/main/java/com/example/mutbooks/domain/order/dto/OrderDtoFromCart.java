package com.example.mutbooks.domain.order.dto;

import com.example.mutbooks.domain.order.entity.enumulation.OrderType;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoFromCart implements Serializable {
    private String username;
    private OrderType orderType;
    private int totalPrice;
}
