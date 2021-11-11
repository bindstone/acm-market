package com.bindstone.acm.market.dto;

import com.bindstone.acm.market.dto.entity.ProductDto;
import com.bindstone.acm.market.entity.Product;

import java.util.Objects;

// tag::asciidoc[]
/**
 * Transformation of the Entity (JPA) Product object to a external REST Object.
 * This could be replaced by an implementation of as example  the framework MapStruct.
 */
public class ProductMapper implements DtoMapper<Product, ProductDto>{
    /**
     * Transform JPA -> DTO (Product)
     * @param obj JPA Product object
     * @return DTO Product object
     */
    @Override
    public ProductDto toDto(Product obj) {
        if(Objects.isNull(obj)) {
            return null;
        }
        ProductDto rtn = new ProductDto();
        rtn.setId(obj.getId());
        rtn.setName(obj.getName());
        rtn.setCurrency(obj.getCurrency());
        rtn.setPrice(obj.getPrice());
        rtn.setPriceEur(obj.getPriceEur());
        rtn.setCreated(obj.getCreated());
        rtn.setUpdated(obj.getUpdated());
        return rtn;
    }

    /**
     * Transform DTO -> JPA (Product)
     * @param obj DTO Product object
     * @return JPA Product object
     */
    @Override
    public Product fromDto(ProductDto obj) {
        if(Objects.isNull(obj)) {
            return null;
        }
        Product rtn = new Product();
        rtn.setId(obj.getId());
        rtn.setName(obj.getName());
        rtn.setCurrency(obj.getCurrency());
        rtn.setPrice(obj.getPrice());
        rtn.setPriceEur(obj.getPriceEur());
        rtn.setCreated(obj.getCreated());
        rtn.setUpdated(obj.getUpdated());
        return rtn;
    }
}
// end::asciidoc[]
