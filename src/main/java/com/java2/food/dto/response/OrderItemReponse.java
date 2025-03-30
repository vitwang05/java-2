package com.java2.food.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemReponse {
    Long id;
    Long order;
    Long food;
    int quantity;
    Double price;
}
