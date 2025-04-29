package com.example.ScopistoTechnical.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Embeddable
public class Money implements Comparable<Money> {

    private BigDecimal amount;

    public Money() {
        amount = BigDecimal.ZERO;
    }

    public Money(BigDecimal amount) {
        this.amount = amount != null ? amount : BigDecimal.ZERO;
    }

    public void subtract(Money other) {
        if (other == null) throw new IllegalArgumentException("Money to subtract cannot be null");
        this.amount = this.amount.subtract(other.amount);
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.compareTo(money.amount) == 0; // use compareTo for BigDecimal
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros());
    }

    @Override
    public int compareTo(Money o) {
        return Integer.compare(this.amount.compareTo(o.amount), 0);
    }
}
