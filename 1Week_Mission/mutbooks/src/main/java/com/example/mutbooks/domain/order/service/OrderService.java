package com.example.mutbooks.domain.order.service;

import com.example.mutbooks.domain.cart.service.CartService;
import com.example.mutbooks.domain.order.dto.OrderDtoFromCart;
import com.example.mutbooks.domain.order.dto.OrderFormDto;
import com.example.mutbooks.domain.order.entity.Order;
import com.example.mutbooks.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public Order save(OrderDtoFromCart orderDtoFromCart, OrderFormDto orderFormDto) {

        Order order = Order.builder()
                .username(orderDtoFromCart.getUsername())
                .totalPrice(orderDtoFromCart.getTotalPrice())
                .orderType(orderDtoFromCart.getOrderType())
                .orderedAt(LocalDateTime.now())
                .requests(orderFormDto.getRequests())
                .payStatus(PayStatus.CONTINUE)
                .orderStatus(OrderStatus.BEFOREORDER)
                .build();

        order.setCreatedDate(LocalDateTime.now());
        return orderRepository.save(order);
    }


}
