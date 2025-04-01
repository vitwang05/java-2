package com.java2.food.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartReponse {
    Long id;
    Long userId;
    FoodReponse food;
    int quantity;
}
