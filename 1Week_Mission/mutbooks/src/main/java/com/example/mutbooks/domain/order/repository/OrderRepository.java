package com.example.mutbooks.domain.order.repository;

import com.example.mutbooks.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
