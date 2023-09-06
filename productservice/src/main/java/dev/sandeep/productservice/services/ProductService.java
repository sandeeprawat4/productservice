package dev.sandeep.productservice.services;

import dev.sandeep.productservice.dtos.GenericProductDto;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id);
}
