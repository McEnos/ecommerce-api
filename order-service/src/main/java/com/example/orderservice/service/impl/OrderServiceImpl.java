package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.feign.InventoryClient;
import com.example.orderservice.models.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public boolean placeOrder(OrderRequest orderRequest) {
        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (inStock) {
            Order order = mapToOrder(orderRequest);
            orderRepository.save(order);
            return true;
        } else {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " not in stock");
        }


    }

    private Order mapToOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .skuCode(orderRequest.skuCode())
                .build();
        return order;
    }
}
