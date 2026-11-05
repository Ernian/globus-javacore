package org.example.equals_hashcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMoney {

    @Test
    @DisplayName("Неверно реализованный hashCode позволяет хранить дубликаты в Set")
    void test1() {
        MoneyFailedHashCode money1 = new MoneyFailedHashCode(new BigDecimal(100), Currency.RUB);
        MoneyFailedHashCode money2 = new MoneyFailedHashCode(new BigDecimal(100), Currency.RUB);
        Set<MoneyFailedHashCode> set = new HashSet<>();

        set.add(money1);
        set.add(money2);

        assertEquals(money1, money2);
        assertEquals(2, set.size());
    }

    @Test
    @DisplayName("Верно реализованный hashCode не позволяет хранить дубликаты в Set")
    void test2() {
        MoneyRightHashCode money1 = new MoneyRightHashCode(new BigDecimal(100), Currency.RUB);
        MoneyRightHashCode money2 = new MoneyRightHashCode(new BigDecimal(100), Currency.RUB);
        Set<MoneyRightHashCode> set = new HashSet<>();

        set.add(money1);
        set.add(money2);

        assertEquals(money1, money2);
        assertEquals(1, set.size());
    }
}