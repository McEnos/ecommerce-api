package com.example.productservice.service.impl;

import com.example.productservice.ProductServiceApplication;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .description(productRequest.description())
                .price(productRequest.price())
                .name(productRequest.name())
                .build();
        Product savedProduct = productRepository.save(product);
        return new ProductResponse(savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map((value) -> new ProductResponse(
                        value.getId(),
                        value.getName(),
                        value.getDescription(),
                        value.getPrice()
                )).toList();
    }
}
