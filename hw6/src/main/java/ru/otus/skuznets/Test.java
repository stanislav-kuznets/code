package ru.otus.skuznets;
import org.junit.Assert;

class Test {
    @org.junit.jupiter.api.Test
    void add() {
        Atm atm = new Atm(500, 1, 100, 3);
        atm.add(500, 2, 100, 1);
        Assert.assertEquals(1900, atm.amountOfMoney());
    }

    @org.junit.jupiter.api.Test
    void withdraw() {
        Atm atm = new Atm(500, 2, 100, 1);
        atm.withdraw(500);
        Assert.assertEquals(600, atm.amountOfMoney());
    }
}