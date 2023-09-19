package dev.sandeep.productservice.services;

import dev.sandeep.productservice.dtos.GenericProductDto;
import dev.sandeep.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
}
