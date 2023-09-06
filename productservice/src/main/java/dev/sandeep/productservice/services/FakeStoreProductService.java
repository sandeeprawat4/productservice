package dev.sandeep.productservice.services;

import dev.sandeep.productservice.dtos.FakeStoreProductDto;
import dev.sandeep.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService  implements  ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, product, GenericProductDto.class
        );
        return response.getBody();
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto  = response.getBody();
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;
    }
}
