package com.java2.food.repository;

import com.java2.food.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Query("SELECT o FROM Order o LEFT JOIN o.orderItems oi WHERE o.id = :orderId ")
    Optional<Order> findOrderWithItems(@Param("orderId") Long orderId);
}
