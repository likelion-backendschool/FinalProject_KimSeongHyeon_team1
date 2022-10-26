package com.example.mutbooks.domain.order.entity.enumulation;

public enum OrderType {
    BULK("일괄배송"), EACH("순차배송");

    private final String description;

    OrderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
