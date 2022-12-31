package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {

    public static FoodDto convertRequestModelToDto(FoodDetailsRequestModel food) {
        return FoodDto.builder()
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodPrice(food.getFoodPrice())
                .build();
    }

    public static FoodEntity convertDtoToEntity(FoodDto food) {
        return FoodEntity.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodPrice(food.getFoodPrice())
                .build();
    }

    public static FoodDto convertEntityToDto(FoodEntity food) {
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodPrice(food.getFoodPrice())
                .build();
    }

    public static FoodDetailsResponse convertDtoToResponse(FoodDto food) {
        return FoodDetailsResponse.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodCategory(food.getFoodCategory())
                .foodPrice(food.getFoodPrice())
                .build();
    }
}
