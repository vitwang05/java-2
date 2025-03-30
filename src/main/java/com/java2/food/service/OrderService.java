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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderItemService orderItemService;
    OrderMapper orderMapper;

    @Transactional
    public OrderResponse placeOrder(OrderCreationRequest request){
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Order order  = orderMapper.toOrder(request);
        try{
            order = orderRepository.save(order);

        }catch (DataAccessException exception){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
        orderItemService.processOrder(request.getUserId(),order);
        return orderMapper.toOrderResponse(order);
    }

}
