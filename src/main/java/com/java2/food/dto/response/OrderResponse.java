package com.java2.food.dto.response;

import com.java2.food.entity.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;

    UserReponse user;

    Double amount;


    String address;


    String status ;


    Date date ;

    Boolean payment ;

    List<OrderItemReponse> orderItems;
}
