package com.driver.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static OrderDto convertRequestModelToDto(OrderDetailsRequestModel order) {
        return OrderDto.builder()
                .items(order.getItems())
                .cost(order.getCost())
                .userId(order.getUserId())
                .build();
    }

    public static OrderEntity convertDtoToEntity(OrderDto order) {
        return OrderEntity.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(order.isStatus())
                .build();
    }

    public static OrderDto convertEntityToDto(OrderEntity order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(order.isStatus())
                .build();
    }

    public static OrderDetailsResponse convertDtoToResponse(OrderDto order) {
        return OrderDetailsResponse.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(order.isStatus())
                .build();
    }
}
