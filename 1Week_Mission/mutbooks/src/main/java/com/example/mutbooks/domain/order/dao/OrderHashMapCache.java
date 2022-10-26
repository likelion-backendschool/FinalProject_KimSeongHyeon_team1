package com.example.mutbooks.domain.order.dao;

import com.example.mutbooks.domain.order.dto.OrderDtoFromCart;
import com.example.mutbooks.domain.order.exception.OrderProcessFailedException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderHashMapCache {

    private final Map<String, OrderDtoFromCart> cache = new ConcurrentHashMap<>();

    public void putOrderDtoFromCart(OrderDtoFromCart orderDtoFromCart, String userNickname) {
        cache.put(userNickname, orderDtoFromCart);
    }

    public OrderDtoFromCart getOrderDtoFromCart(String username) {
        if (username != null && cache != null && cache.containsKey(username)) {
            return cache.get(username);
        } else {
            throw new OrderProcessFailedException("주문 절차에서 예외가 발생했습니다.");
        }
    }

    public void removeOrderDtoFromCart(String username) {
        if (username != null && cache != null && cache.containsKey(username)) {
            cache.remove(username);
        } else {
            throw new OrderProcessFailedException("주문 절차에서 예외가 발생했습니다.");
        }
    }

    public int getCount() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
    }
}
