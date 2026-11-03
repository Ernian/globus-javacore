package org.example.equals_hashcode;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Money {
    protected final Currency currency;
    protected final BigDecimal amount;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return new BigDecimal(amount.toString());
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return currency == money.currency && Objects.equals(amount, money.amount);
    }
}