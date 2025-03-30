package com.java2.food.service;


import com.java2.food.entity.Cart;
import com.java2.food.entity.Order;
import com.java2.food.entity.OrderItems;
import com.java2.food.entity.Users;
import com.java2.food.mapper.CartMapper;
import com.java2.food.repository.CartRepository;
import com.java2.food.repository.OrderItemRepository;
import com.java2.food.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderItemService {
    OrderItemRepository orderItemRepository;
    CartRepository cartRepository;
    CartMapper cartMapper;

    public void processOrder(Long userId,Order order) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty for user: " + userId);
        }

        List<OrderItems> orderItems = cartItems.stream()
                .map(cart -> cartMapper.toOrderItem(cart, order))
                .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);
        cartRepository.deleteAllByUserId(userId);
    }
}
