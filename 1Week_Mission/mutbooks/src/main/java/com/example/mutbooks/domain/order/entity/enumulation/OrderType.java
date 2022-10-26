package com.example.mutbooks.domain.order.entity.enumulation;

public enum OrderType {
    BEFORE("주문 전"), ORDERED("주문완료");

    private final String description;

    OrderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
