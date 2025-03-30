package com.java2.food.service;


import com.java2.food.dto.request.FoodCreationRequest;
import com.java2.food.dto.response.FoodReponse;
import com.java2.food.entity.Foods;
import com.java2.food.exception.AppException;
import com.java2.food.exception.ErrorCode;
import com.java2.food.mapper.FoodMapper;
import com.java2.food.repository.FoodRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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

    public FoodReponse createFood(FoodCreationRequest request){
        Foods food = foodMapper.toFood(request);
        try{
            food = foodRepository.save(food);
        }catch (DataAccessException exception){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
        return foodMapper.toFoodReponse(food);
    }

    public void deleteFood(Long foodId){
        try{
            if (!foodRepository.existsById(foodId)){
                throw new AppException(ErrorCode.FOOD_NOT_EXISTED);
            }
            foodRepository.deleteById(foodId);
        }catch (DataAccessException exception){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }

    }
}
