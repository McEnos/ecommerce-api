package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;

public interface OrderService {
    boolean placeOrder(OrderRequest orderRequest);
}
