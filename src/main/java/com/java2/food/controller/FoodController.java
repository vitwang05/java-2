package com.java2.food.controller;

import com.java2.food.dto.request.ApiResponse;
import com.java2.food.dto.response.FoodReponse;
import com.java2.food.service.FoodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodController {
    FoodService foodService;

    @GetMapping
    ApiResponse<List<FoodReponse>> getFoods() {
        return ApiResponse.<List<FoodReponse>>builder().result(foodService.getFood()).build();
    }
}
