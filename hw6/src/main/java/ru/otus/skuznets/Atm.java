package ru.otus.skuznets;

import java.util.HashMap;
import java.util.Map;

public class Atm {
    private Cell cell1;
    private Cell cell2;
    int nominalOne;
    int nominalTwo;

    Map<Integer, Cell> allmoney = new HashMap<Integer, Cell>();

    public Atm(int nominalOne, int numberOfNominalOne, int nominalTwo, int numberOfNominalTwo) {
        this.cell1 = new Cell(numberOfNominalOne, nominalOne);
        this.cell2 = new Cell(numberOfNominalTwo, nominalTwo);
        this.nominalOne = nominalOne;
        this.nominalTwo = nominalTwo;
    }

    public void add(int nominalOne, int numberOfNominalOne, int nominalTwo, int numberOfNominalTwo) {
        System.out.println("Прием наличных");
        cell1.add(numberOfNominalOne);
        cell2.add(numberOfNominalTwo);

        System.out.println("Получено банкнот: " + nominalOne + " - " + numberOfNominalOne +", " + nominalTwo + " - " + numberOfNominalTwo +
        "\nБаланс пополнен на " + (numberOfNominalOne * nominalOne + numberOfNominalTwo * nominalTwo));
    }

    public void withdraw(int amount) {
        System.out.println("Выдача наличных");
        int numberOfNominalOne, numberOfNominalTwo;
        if(amount <= 0) {
            throw new IllegalArgumentException("Запрошенная сумма не может быть меньше или равна 0");
        }
        System.out.println("Запрошена сумма: " + amount);
        if (amountOfMoney() < amount) {
            throw new IllegalArgumentException("Выдача запрошенной суммы невозможна, максимальная сумма = " + amountOfMoney());
        }
        numberOfNominalOne = cell1.checkAvailabilityOfBanknotes(amount);
        amount -= numberOfNominalOne * nominalOne;
        numberOfNominalTwo = cell2.checkAvailabilityOfBanknotes(amount);
        amount -= numberOfNominalTwo * nominalTwo;

        if(amount != 0) {
            throw new IllegalArgumentException("Выдача запрошенной суммы невозможна");
        } else {
            cell1.withdraw(numberOfNominalOne);
            cell2.withdraw(numberOfNominalTwo);
            System.out.println("Выдано банкнотами: nominalOne - " + numberOfNominalOne + ", nominalTwo - " + numberOfNominalTwo);
        }
    }
    public String toString() {
            return "Состояние банкомата: " + nominalOne + " - " + cell1 + ", " + nominalTwo + " - " + cell2 + "\n" + "Сумма: " + amountOfMoney();
    }
    public long amountOfMoney() {
        return  cell1.getNumberOfBanknotesInCell() * nominalOne + cell2.getNumberOfBanknotesInCell() * nominalTwo;
    }
}
