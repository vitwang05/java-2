package com.java2.food.service;


import com.java2.food.dto.request.OrderCreationRequest;
import com.java2.food.dto.response.OrderResponse;
import com.java2.food.entity.Order;
import com.java2.food.entity.Users;
import com.java2.food.exception.AppException;
import com.java2.food.exception.ErrorCode;
import com.java2.food.mapper.OrderMapper;
import com.java2.food.repository.OrderRepository;
import com.java2.food.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderItemService orderItemService;
    OrderMapper orderMapper;
    CartService cartService;

    @Transactional
    public OrderResponse placeOrder(OrderCreationRequest request){
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Order order  = orderMapper.toOrder(request);
        order.setUser(user);


        int amount = cartService.totalItem(request.getUserId());

        order.setAmount((double)amount);

        try{
            order = orderRepository.save(order);
        }catch (DataAccessException exception){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
        orderItemService.processOrder(request.getUserId(),order);
        return orderMapper.toOrderResponse(order);
    }


    public List<OrderResponse> getAllOrder(){
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse getOrderDetail(Long orderId){
        Order order = orderRepository.findOrderWithItems(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return orderMapper.toOrderResponse(order);
    }
}
