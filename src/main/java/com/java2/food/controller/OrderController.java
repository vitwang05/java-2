package com.java2.food.controller;


import com.java2.food.dto.request.ApiResponse;
import com.java2.food.dto.request.OrderCreationRequest;
import com.java2.food.dto.response.OrderResponse;
import com.java2.food.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping("/placeOrder")
    ApiResponse<OrderResponse> placeOrder(@Valid @RequestBody OrderCreationRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.placeOrder(request))
                .build();
    }


    @GetMapping("/getAllOrder")
    ApiResponse<List<OrderResponse>> getAllOrder(){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrder())
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderDetails(@PathVariable Long orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrderDetail(orderId))
                .build();
    }
}
