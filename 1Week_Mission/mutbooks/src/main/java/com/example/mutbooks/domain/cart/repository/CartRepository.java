package com.example.mutbooks.domain.cart.repository;

import com.example.mutbooks.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(Long id);

    List<Cart> findByUsernameAndIsOrdered(String nickname, boolean isOrdered);
}

