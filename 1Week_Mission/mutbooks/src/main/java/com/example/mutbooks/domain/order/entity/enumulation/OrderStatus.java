package com.example.mutbooks.domain.order.entity.enumulation;

public enum OrderStatus {
    ORDERED("주문완료"), CANCEL("주문취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
