package com.java2.food.mapper;

import com.java2.food.dto.request.FoodCreationRequest;
import com.java2.food.dto.response.FoodReponse;
import com.java2.food.entity.Foods;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Foods toFood(FoodCreationRequest request);

    FoodReponse toFoodReponse(Foods food);
}
