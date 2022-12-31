package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto food = foodService.getFoodById(id);
		return FoodConverter.convertDtoToResponse(food);
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto food = foodService.createFood(FoodConverter.convertRequestModelToDto(foodDetails));
		return FoodConverter.convertDtoToResponse(food);
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto food = foodService.updateFoodDetails(id, FoodConverter.convertRequestModelToDto(foodDetails));
		return FoodConverter.convertDtoToResponse(food);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDetailsResponse> foodDetailsResponse = new ArrayList<>();
		List<FoodDto> foodDto = foodService.getFoods();

		for(FoodDto food : foodDto) {
			foodDetailsResponse.add(FoodConverter.convertDtoToResponse(food));
		}
		return foodDetailsResponse;
	}
}
