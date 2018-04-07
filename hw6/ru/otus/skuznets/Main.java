package ru.otus.skuznets;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(2, 3,5);
        System.out.println(atm);
        atm.add(4,2,4);
        System.out.println(atm);
        atm.withdraw(1100);
        System.out.println(atm);
    }
}
