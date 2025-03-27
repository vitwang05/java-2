package com.java2.food.service;


import com.java2.food.dto.response.FoodReponse;
import com.java2.food.mapper.FoodMapper;
import com.java2.food.repository.FoodRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodService {
    FoodRepository foodRepository;
    FoodMapper foodMapper;
    public List<FoodReponse> getFood() {
        return foodRepository.findAll().stream().map(foodMapper::toFoodReponse).toList();
    }
}
