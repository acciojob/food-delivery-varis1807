package com.driver.ui.controller;

import java.util.*;

import com.driver.Converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.*;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto order = orderService.getOrderById(id);
		return OrderConverter.convertDtoToResponse(order);
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto = orderService.createOrder(OrderConverter.convertRequestModelToDto(order));
		return OrderConverter.convertDtoToResponse(orderDto);
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = orderService.updateOrderDetails(id, OrderConverter.convertRequestModelToDto(order));
		return OrderConverter.convertDtoToResponse(orderDto);
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> orderDetailsResponse = new ArrayList<>();
		List<OrderDto> orderDto = orderService.getOrders();

		for(OrderDto order : orderDto) {
			orderDetailsResponse.add(OrderConverter.convertDtoToResponse(order));
		}
		return orderDetailsResponse;
	}
}
