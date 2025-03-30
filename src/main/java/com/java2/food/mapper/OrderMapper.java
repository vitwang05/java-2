package com.java2.food.mapper;

import com.java2.food.dto.request.OrderCreationRequest;
import com.java2.food.dto.response.OrderResponse;
import com.java2.food.dto.response.UserReponse;
import com.java2.food.entity.Order;
import com.java2.food.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    Order toOrder(OrderCreationRequest request);

    @Mapping(target = "user", source = "user")
    OrderResponse toOrderResponse(Order order);



    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    UserReponse usersToUserResponse(Users user);
}
