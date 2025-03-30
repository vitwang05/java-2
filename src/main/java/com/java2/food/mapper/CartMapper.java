package com.java2.food.mapper;


import com.java2.food.dto.request.CartCreationRequest;
import com.java2.food.dto.response.CartReponse;
import com.java2.food.entity.*;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "food", source = "food")
    Cart toCart(CartCreationRequest request, Users user, Foods food);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "foodId", source = "food.id")
    CartReponse toCartResponse(Cart cart);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "cart.food", target = "food")
    @Mapping(source = "cart.quantity", target = "quantity")
    @Mapping(source = "cart.price", target = "price")
    @Mapping(target = "order", source = "order")
    OrderItems toOrderItem(Cart cart, Order order);
}
