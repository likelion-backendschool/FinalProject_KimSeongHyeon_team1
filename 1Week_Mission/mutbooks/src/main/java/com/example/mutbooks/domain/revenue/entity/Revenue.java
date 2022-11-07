package com.example.mutbooks.domain.revenue.entity;

import com.example.mutbooks.domain.order.entity.enumulation.OrderStatus;
import com.example.mutbooks.domain.order.entity.enumulation.OrderType;
import com.example.mutbooks.domain.pay.entity.enumulation.PayStatus;
import com.example.mutbooks.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REVENUES")
public class Revenue extends BaseEntity {
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
}
