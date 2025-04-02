package com.java2.food.controller;

import com.java2.food.dto.request.ApiResponse;
import com.java2.food.dto.request.FoodCreationRequest;
import com.java2.food.dto.response.FoodReponse;
import com.java2.food.service.FoodService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")

public class FoodController {
    FoodService foodService;

    @GetMapping
    ApiResponse<List<FoodReponse>> getFoods() {
        return ApiResponse.<List<FoodReponse>>builder().result(foodService.getFood()).build();
    }

    @PostMapping
    ApiResponse<FoodReponse> createFood(@RequestBody @Valid FoodCreationRequest request) {
        return ApiResponse.<FoodReponse>builder()
                .result(foodService.createFood(request))
                .build();

    }
    @DeleteMapping("/{foodId}")
    ApiResponse<String> deleteFood(@PathVariable Long foodId) {
        foodService.deleteFood(foodId);
        return ApiResponse.<String>builder().result("Food has been deleted").build();
    }

    @GetMapping("/search")
    ApiResponse<List<FoodReponse>> search(@RequestParam String keyword){
        return ApiResponse.<List<FoodReponse>>builder()
                .result(foodService.SearchFood(keyword))
                .build();
    }
}
