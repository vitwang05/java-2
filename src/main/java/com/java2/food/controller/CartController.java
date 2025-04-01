package com.java2.food.controller;

import com.java2.food.dto.request.ApiResponse;
import com.java2.food.dto.request.CartCreationRequest;
import com.java2.food.dto.response.CartReponse;
import com.java2.food.service.CartService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class CartController {

    CartService cartService;

    @GetMapping("/{userId}")
    ApiResponse<List<CartReponse>> getCart(@PathVariable Long userId){
        return ApiResponse.<List<CartReponse>>builder()
                .result(cartService.getCart(userId))
                .build();
    }


    @PostMapping
    ApiResponse<CartReponse> addToCart(@Valid @RequestBody CartCreationRequest request){
        return ApiResponse.<CartReponse>builder()
                .result(cartService.addToCart(request))
                .build();
    }

    @DeleteMapping("/removeCartItem/{cartId}")
    ApiResponse<String> removeCardItem(@PathVariable Long cartId) {
        cartService.removeCartItem(cartId);
        return ApiResponse.<String>builder().result("Cart item has been removed").build();
    }

    @DeleteMapping("/removeALlCard/{userId}")
    ApiResponse<String> removeAllCardItem(@PathVariable Long userId) {
        cartService.removeCartItem(userId);
        return ApiResponse.<String>builder().result("All Cart has been removed").build();
    }
}
