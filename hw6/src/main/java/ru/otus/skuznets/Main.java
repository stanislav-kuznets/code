package ru.otus.skuznets;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm(500, 3, 100, 2);
        System.out.println(atm);
        atm.add(500, 4, 100, 2);
        System.out.println(atm);
        atm.withdraw(1200);
        System.out.println(atm);
    }
}
