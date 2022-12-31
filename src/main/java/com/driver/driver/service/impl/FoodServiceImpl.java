package com.driver.service.impl;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = foodRepository.save(FoodConverter.convertDtoToEntity(food));
        return FoodConverter.convertEntityToDto(foodEntity);
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        return FoodConverter.convertEntityToDto(foodEntity);
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());

        foodRepository.save(foodEntity);
        return FoodConverter.convertEntityToDto(foodEntity);
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        foodRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto> foodDto = new ArrayList<>();
        List<FoodEntity> foodEntity = (List<FoodEntity>) foodRepository.findAll();

        for(FoodEntity food : foodEntity) {
            foodDto.add(FoodConverter.convertEntityToDto(food));
        }
        return foodDto;
    }
}