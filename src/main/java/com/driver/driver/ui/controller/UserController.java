package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Converter.FoodConverter;
import com.driver.Converter.UserConverter;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.*;
import com.driver.service.UserService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.UserDto;
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
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto user = userService.getUserByUserId(id);
		return UserConverter.convertDtoToResponse(user);
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto user = userService.createUser(UserConverter.convertRequestModelToDto(userDetails));
		return UserConverter.convertDtoToResponse(user);
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto user = userService.updateUser(id, UserConverter.convertRequestModelToDto(userDetails));
		return UserConverter.convertDtoToResponse(user);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		userService.deleteUser(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserResponse> userResponse = new ArrayList<>();
		List<UserDto> userDto = userService.getUsers();

		for(UserDto user : userDto) {
			userResponse.add(UserConverter.convertDtoToResponse(user));
		}
		return userResponse;
	}
	
}
