package ru.otus.skuznets;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static ATMDepartment atmDepartment;

    static {
        atmDepartment = ATMDepartment.getAtmDepartment();
    }

    public static void main(String[] args) {
        List<Cell> cells = new ArrayList();

        cells.add(new Cell(50,2));
        cells.add(new Cell(1000, 4));
        ATM atm = new ATM(cells);
        atmDepartment.add(atm);

        cells.add(new Cell(500,3));
        cells.add(new Cell(5000,1));
        ATM atm2 = new ATM(cells);
        atmDepartment.add(atm2);

        System.out.println("Первый банкомат, исходное состояние: " + atm.getBalance());
        System.out.println("Второй банкомат, исходное состояние: " + atm2.getBalance());

        atm.withDraw(100);
        atm2.withDraw(500);

        System.out.println("Первый банкомат, после выдачи: " + atm.getBalance());
        System.out.println("Второй банкомат, после выдачи: " + atm2.getBalance());

        System.out.println("Баланс департамента: " + atmDepartment.getTotalBalance());

        atmDepartment.restoreAllATM();
        System.out.println("Первый банкомат, восстановление исходного состояния: " + atm.getBalance());
        System.out.println("Второй банкомат, восстановление исходного состояния: " + atm2.getBalance());
    }
}
