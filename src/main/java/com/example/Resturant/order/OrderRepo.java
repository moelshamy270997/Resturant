package com.example.Resturant.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<Order> findOrderById(Long id);
}
