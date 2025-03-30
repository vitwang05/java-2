package com.java2.food.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodCreationRequest {
    @Size(min = 2, message = "INVALID_NAME")
    String name;

    @Min(value = 1, message = "INVALID_PRICE")
    @Max(value = 100000000, message = "INVALID_PRICE")
    Long price;

    String image;

    String category;
}
