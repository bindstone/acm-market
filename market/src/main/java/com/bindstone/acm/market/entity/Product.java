package com.bindstone.acm.market.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
// tag::asciidoc[]
/**
 * Entity for Database Table PRODUCT
 */
@Entity
public class Product {

    /**
     * Primary Key of Table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Product name
     */
    @Column(name = "PRODUCT_NAME")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 200, message = "Product name must be at least 2 and maximal 200 character length")
    private String name;

    /**
     * Currency of product represented in 3 chars
     */
    @Column(name = "CURRENCY")
    @NotBlank(message = "Currency is mandatory")
    @Size(min = 3, max = 3, message = "Currency in exact 3 character length")
    private String currency;

    /**
     * Price in Product currency
     */
    @Column(name = "PRICE")
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "negative price not allowed")
    private BigDecimal price;

    /**
     * Price in EUR currency
     */
    @Column(name = "PRICE_EUR")
    @NotNull(message = "Price is mandatory")
    private BigDecimal priceEur;

    /**
     * Creation Date (Audit Value)
     */
    @Column(name = "CREATION_DATE")
    @NotNull(message = "Creation date is mandatory")
    private LocalDateTime created;

    /**
     * Modification Date (Audit Value)
     */
    @Column(name = "UPDATE_DATE")
    @NotNull(message = "Update date is mandatory")
    @Version
    private LocalDateTime updated;
    // end::asciidoc[]

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(BigDecimal priceEur) {
        this.priceEur = priceEur;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", price=").append(price);
        sb.append(", priceEur=").append(priceEur);
        sb.append(", updated=").append(updated);
        sb.append(", created=").append(created);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(name, product.name)
                && Objects.equals(currency, product.currency)
                && Objects.equals(price, product.price)
                && Objects.equals(priceEur, product.priceEur)
                && Objects.equals(updated, product.updated)
                && Objects.equals(created, product.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, currency, price, priceEur, updated, created);
    }
}
