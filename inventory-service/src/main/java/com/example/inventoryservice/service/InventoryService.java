package com.example.inventoryservice.service;

public interface InventoryService {
   public boolean isInStock(String skuCode, Integer quantity);
}
