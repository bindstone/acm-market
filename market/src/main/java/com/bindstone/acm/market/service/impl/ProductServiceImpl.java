package com.bindstone.acm.market.service.impl;

import com.bindstone.acm.market.dto.ProductMapper;
import com.bindstone.acm.market.dto.entity.ProductDto;
import com.bindstone.acm.market.entity.Product;
import com.bindstone.acm.market.repository.ProductRepository;
import com.bindstone.acm.market.service.CurrencyRestService;
import com.bindstone.acm.market.service.ProductService;
import com.bindstone.acm.market.service.exception.UnknownProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// tag::asciidoc[]

/**
 * Product Service
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final CurrencyRestService currencyRestService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * Constructor injecting the dependencies and create the Product Mapper for the DTO
     * @param currencyRestService Currency Rest Service to communicate with external service
     * @param productRepository Product Repository to query the Database
     */
    public ProductServiceImpl(CurrencyRestService currencyRestService, ProductRepository productRepository) {
        this.currencyRestService = currencyRestService;
        this.productRepository = productRepository;
        this.productMapper = new ProductMapper();
    }

    /**
     * Get the Product list from database
     * @return List of products
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductList() {
        logger.info("Get all products");
        return productRepository.findAll();
    }

    /**
     * Get the Product list from database wrapped with DTO transformation
     * @return List of products (DTO)
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductDtoList() {
        return getProductList().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Create a new Product, calculate the exchange value, and store in Database
     * @param product new Product
     * @return created Product (include ID and timestamps)
     */
    @Override
    @Transactional
    public Product create(Product product) {
        logger.info("Create new product");
        var euro = currencyRestService.calculateExchange(product.getCurrency(), product.getPrice());
        product.setCreated(LocalDateTime.now());
        product.setPriceEur(euro);
        return productRepository.save(product);
    }

    /**
     * Create a new Product, calculate the exchange value, and store in Database, wrapped with DTO transformation
     * @param product new Product as DTO
     * @return created Product (include ID and timestamps) (DTO)
     */
    @Override
    @Transactional
    public ProductDto create(ProductDto product) {
        var jpa = productMapper.fromDto(product);
        return productMapper.toDto(create(jpa));
    }

    /**
     * Update Product, calculate the exchange value in case the currency or value changed,
     * and store in Database.
     * @param product Product
     * @return Product (include ID and timestamps)
     */
    @Override
    @Transactional
    public Product update(Product product) {
        logger.info("Update product");
        // Business Rules validation
        // - Example, past, current exchange rate...
        var oldProduct = productRepository.findById(product.getId()).orElseThrow(() -> new UnknownProductException(product.getId()));
        if (!oldProduct.getPrice().equals(product.getPrice()) || !oldProduct.getCurrency().equals(product.getCurrency())) {
            var euro = currencyRestService.calculateExchange(product.getCurrency(), product.getPrice());
            product.setPriceEur(euro);
        }
        return productRepository.save(product);
    }

    /**
     * Update Product, calculate the exchange value in case the currency or value changed,
     * and store in Database, wrapped with DTO transformation.
     * @param product Product as DTO
     * @return Product (include ID and timestamps) (DTO)
     */
    @Override
    @Transactional
    public ProductDto update(ProductDto product) {
        var jpa = productMapper.fromDto(product);
        return productMapper.toDto(update(jpa));
    }

    /**
     * Delete (Remove) a product from the market
     * @param id Product id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("Delete product");
        var product = productRepository.findById(id).orElseThrow(() -> new UnknownProductException(id));
        productRepository.delete(product);
    }
}
// end::asciidoc[]