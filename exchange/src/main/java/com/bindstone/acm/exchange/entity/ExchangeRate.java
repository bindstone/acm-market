package com.bindstone.acm.exchange.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
// tag::asciidoc[]
/**
 * Entity for Database Table EXCHANGE_RATE
 */
@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate {

    /**
     * Primary Key of Table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Currency represented in 3 chars
     */
    @Column(name = "ISO_CCY", unique = true)
    @Size(min = 3, max = 3, message = "Currency in exact 3 character length")
    @NotNull(message = "Currency is mandatory")
    private String ccy;

    /**
     * Exchange Rate handled as BigDecimal
     */
    @Column(name = "EXCHANGE_RATE")
    @Min(value = 0, message = "negative price not allowed")
    @NotNull(message = "Rate is mandatory")
    private BigDecimal rate;

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

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal price) {
        this.rate = price;
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
        final StringBuilder sb = new StringBuilder("ExchangeRate{");
        sb.append("id=").append(id);
        sb.append(", ccy='").append(ccy).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", updated=").append(updated);
        sb.append(", created=").append(created);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(id, that.id)
                && Objects.equals(ccy, that.ccy)
                && Objects.equals(updated, that.updated)
                && Objects.equals(created, that.created)
                && Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ccy, rate, updated, created);
    }
}
