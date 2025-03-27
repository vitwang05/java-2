package com.java2.food.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class FoodCreationRequest {
    @Size(min = 2, message = "INVALID_NAME")
    String name;

    @DecimalMin(value = "0.01", message = "MUST_BE_GREATER_THAN_0")
    @DecimalMin(value = "10000000.00", message = "MUST_BE_SMALLER_THAN_10.000.000")
    BigDecimal price;

    String image;

    String category;
}
