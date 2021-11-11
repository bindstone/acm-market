package com.bindstone.acm.market.service;

import com.bindstone.acm.market.dto.entity.ProductDto;
import com.bindstone.acm.market.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();
    List<ProductDto> getProductDtoList();

    Product create(Product product);
    ProductDto create(ProductDto product);

    Product update(Product product);
    ProductDto update(ProductDto product);

    void delete(Long id);


}
