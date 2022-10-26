package com.example.mutbooks.domain.order.entity;

import com.example.mutbooks.domain.order.entity.enumulation.OrderStatus;
import com.example.mutbooks.domain.order.entity.enumulation.OrderType;
import com.example.mutbooks.domain.pay.entity.enumulation.PayStatus;
import com.example.mutbooks.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;
    @Enumerated(value = EnumType.STRING)
    private PayStatus payStatus;

    private LocalDateTime orderedAt;
    private int totalPrice;
    @Lob
    private String requests;

    private String username;

}
