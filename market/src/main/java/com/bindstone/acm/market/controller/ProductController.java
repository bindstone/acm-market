package com.bindstone.acm.market.controller;

import com.bindstone.acm.market.dto.entity.ProductDto;
import com.bindstone.acm.market.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// tag::asciidoc[]

/**
 * Rest Controller for the products in market
 * - Entry Point: /api/v1/product
 * - CrossOrigin for UI to access to information
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieve Product List
     * @return Product List (DTO)
     */
    @GetMapping
    @Operation(summary = "Retrieve the list of products in the market")
    public ResponseEntity<List<ProductDto>> getProductDtoList() {
        return ResponseEntity.ok(productService.getProductDtoList());
    }

    /**
     * Update a Product
     * @param product Product (DTO)
     * @return Product (DTO)
     */
    @PutMapping
    @Operation(summary = "Update a product form in the market")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.update(product));
    }

    /**
     * Create new Product
     * @param product Product (DTO)
     * @return Product (DTO)
     */
    @PostMapping
    @Operation(summary = "Create a new product in the market")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.create(product));
    }

    /**
     * Delete Product
     * @param id Product ID
     * @return NOTHING
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a product in the market")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Product ID")
            @PathVariable Long id
    ) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
// end::asciidoc[]
