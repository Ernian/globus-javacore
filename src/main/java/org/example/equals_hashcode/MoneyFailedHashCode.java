package org.example.equals_hashcode;

import java.math.BigDecimal;

public class MoneyFailedHashCode extends Money {
    private static int id;

    public MoneyFailedHashCode(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }

    @Override
    public int hashCode() {
        return id++;
    }
}