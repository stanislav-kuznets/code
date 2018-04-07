package ru.otus.skuznets;

public class Cell {

    private int numberOfBanknotesInCell;
    private int valueOfBanknoteInCell;

    Cell(int numberOfBanknotes, int valueOfBanknote) {
        if(numberOfBanknotes < 0) {
            throw new IllegalArgumentException("Количество банкнот не может быть отрицательным");
        }
        this.numberOfBanknotesInCell = numberOfBanknotes;
        this.valueOfBanknoteInCell = valueOfBanknote;
    }
    void add (int count) {
        numberOfBanknotesInCell += count;
    }
    int getNumberOfBanknotesInCell() {
        return numberOfBanknotesInCell;
    }
    void withdraw(int number) {
        if(number > numberOfBanknotesInCell) {
            throw new IllegalArgumentException("Запрошено больше, чем содержит ячейка");
        } else {
            numberOfBanknotesInCell -= number;
        }
    }
    int checkAvailabilityOfBanknotes(int amount) {
        if(numberOfBanknotesInCell == 0) {
           return 0;
        }
        int amountToWithdraw = 0;
        while(amount / valueOfBanknoteInCell > 0) {
            if (numberOfBanknotesInCell == amountToWithdraw) {
                return amountToWithdraw;
            }
            amountToWithdraw++;
            amount -= valueOfBanknoteInCell;
        }
        return amountToWithdraw;
    }
    public String toString() {
        return String.valueOf(getNumberOfBanknotesInCell());
    }
}
