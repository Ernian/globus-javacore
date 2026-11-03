package org.example.equals_hashcode;

import java.math.BigDecimal;
import java.util.Objects;

public class MoneyRightHashCode extends Money {

    public MoneyRightHashCode(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}