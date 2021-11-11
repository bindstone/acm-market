package com.bindstone.acm.market.dto.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

// tag::asciidoc[]
public class ProductDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 200, message = "Product name must be at least 2 and maximal 200 character length")
    private String name;

    @NotBlank(message = "Currency is mandatory")
    @Size(min = 3, max = 3, message = "Currency in exact 3 character length")
    private String currency;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "negative price not allowed")
    private BigDecimal price;

    private BigDecimal priceEur;

    private LocalDateTime created;

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
        ProductDto product = (ProductDto) o;
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
