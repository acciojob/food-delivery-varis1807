package com.driver.service.impl;

import com.driver.Converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = orderRepository.save(OrderConverter.convertDtoToEntity(order));
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(order.isStatus());

        orderRepository.save(orderEntity);
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        orderRepository.deleteById(Long.valueOf(orderId));
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDto = new ArrayList<>();
        List<OrderEntity> orderEntity = (List<OrderEntity>) orderRepository.findAll();

        for(OrderEntity order : orderEntity) {
            orderDto.add(OrderConverter.convertEntityToDto(order));
        }
        return orderDto;
    }
}