package dev.sandeep.productservice.thirdpartyclients.productservice.fakestore;

import dev.sandeep.productservice.dtos.GenericProductDto;
import dev.sandeep.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductApiPath;


    private String specificProductRequestUrl;

    private String productRequestBaseUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}")
    String fakeStoreApiUrl, @Value("${fakestore.api.paths.product}")
    String fakeStoreProductApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestBaseUrl = fakeStoreApiUrl+fakeStoreProductApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl+fakeStoreProductApiPath+"/{id}";
    }
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
                productRequestBaseUrl, product, GenericProductDto.class
        );
        return response.getBody();
    }

    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto  = response.getBody();
        if(fakeStoreProductDto == null)
            throw new NotFoundException("Product with id: "+ id + "does not exist");
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }

    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> list = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody())
            list.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto));
        return list;
    }

    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }

    private GenericProductDto convertFakeStoreProductToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        return product;
    }
}
