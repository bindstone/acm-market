package com.bindstone.acm.market.repository;

import com.bindstone.acm.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// tag::asciidoc[]
/**
 * Repository interface to Query the Product from DB
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
// end::asciidoc[]