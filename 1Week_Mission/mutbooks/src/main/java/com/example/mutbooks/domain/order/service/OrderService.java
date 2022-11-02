package com.example.mutbooks.domain.order.service;

import com.example.mutbooks.domain.cart.dto.CartDto;
import com.example.mutbooks.domain.cart.service.CartService;
import com.example.mutbooks.domain.order.dto.OrderDtoFromCart;
import com.example.mutbooks.domain.order.dto.OrderFormDto;
import com.example.mutbooks.domain.order.entity.Order;
import com.example.mutbooks.domain.order.entity.enumulation.OrderStatus;
import com.example.mutbooks.domain.order.exception.OrderNotFoundException;
import com.example.mutbooks.domain.order.repository.OrderRepository;
import com.example.mutbooks.domain.pay.dto.PaySuccessDto;
import com.example.mutbooks.domain.pay.entity.enumulation.PayStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                .payStatus(PayStatus.BEFORE)
                .orderStatus(OrderStatus.ORDERED)
                .build();

        order.setCreatedDate(String.valueOf(LocalDateTime.now()));
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        Optional<Order> findOrder = orderRepository.findById(id);

        if (findOrder.isEmpty()) {
            throw new OrderNotFoundException("해당 주문을 찾을 수 없습니다.");
        }

        return findOrder.get();
    }

    public PaySuccessDto getPaySuccessDto(Order order) {

        List<CartDto> orderHistory = cartService.getCartDtoListByUsername(order.getUsername(), true);

        return PaySuccessDto.builder()
                .username(order.getUsername())
                .orderId(order.getId())
                .orderHistory(orderHistory)
                .orderStatus(order.getOrderStatus())
                .orderTotalPrice(order.getTotalPrice())
                .build();
    }

    /* 결제 취소 */
    @Transactional
    public void cancelOrderByUser(Long orderId) {
        Order order = findById(orderId);

        order.setOrderStatus(OrderStatus.CANCEL);
        order.setPayStatus(PayStatus.REFUND);

        cartService.cancelOrder(order.getUsername());
    }

    public List<Order> findAllUsername(String username) {
        return orderRepository.findAllByUsernameOrderByOrderedAtAsc(username);
    }
}
