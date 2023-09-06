package dev.sandeep.productservice.services;

import dev.sandeep.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }
}
