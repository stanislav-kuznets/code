package ru.otus.skuznets;

public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("String size: " + Calc.getStringSize(2000));
        System.out.println("Object size: " + Calc.getObjectSize(2000));
        System.out.println("List size: " + Calc.getListSize(2000));
        System.out.println("ArrayList size: " + Calc.getArrayListSize(2000));
    }
}