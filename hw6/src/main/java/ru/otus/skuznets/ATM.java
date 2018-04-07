package ru.otus.skuznets;

public class ATM {
    private Cell cell1000;
    private Cell cell500;
    private Cell cell100;

    public ATM(int numberOf1000, int numberOf500, int numberOf100) {
        this.cell1000 = new Cell(numberOf1000, 1000);
        this.cell500 = new Cell(numberOf500, 500);
        this.cell100 = new Cell(numberOf100, 100);
    }

    public void add(int numberOf1000, int numberOf500, int numberOf100) {
        System.out.println("Прием наличных");
        cell1000.add(numberOf1000);
        cell500.add(numberOf500);
        cell100.add(numberOf100);

        System.out.println("Получено банкнот: 1000 - " + numberOf1000 +", 500 - " + numberOf500 + ", 100 - " + numberOf100 +
        "\nБаланс пополнен на " + (numberOf1000 * 1000 + numberOf500 * 500 + numberOf100 * 100));
    }

    public void withdraw(int amount) {
        System.out.println("Выдача наличных");
        int numberOf1000, numberOf500, numberOf100;
        if(amount <= 0) {
            throw new IllegalArgumentException("Запрошенная сумма не может быть меньше или равна 0");
        }
        System.out.println("Запрошена сумма: " + amount);
        numberOf1000 = cell1000.checkAvailabilityOfBanknotes(amount);
        amount -= numberOf1000 * 1000;
        numberOf500 = cell500.checkAvailabilityOfBanknotes(amount);
        amount -= numberOf500 * 500;
        numberOf100 = cell100.checkAvailabilityOfBanknotes(amount);
        amount -= numberOf100 * 100;

        if(amount != 0) {
            throw new IllegalArgumentException("Выдача запрошенной суммы невозможна");
        } else {
            cell1000.withdraw(numberOf1000);
            cell500.withdraw(numberOf500);
            cell100.withdraw(numberOf100);
            System.out.println("Выдано банкнотами: 1000 - " + numberOf1000 + ", 500 - " + numberOf500 + ", 100 - " + numberOf100);
        }
    }
    public String toString() {
        return "Состояние банкомата\n" + "Количество банкнот 1000 - " + cell1000 + ", 500 - " + cell500 + ", 100 - " + cell100 + "\n" +
                "Сумма: " + amountOfMoney();
    }
    public long amountOfMoney() {
        return  cell1000.getNumberOfBanknotesInCell() * 1000 + cell500.getNumberOfBanknotesInCell() * 500 + cell100.getNumberOfBanknotesInCell() * 100;
    }
}
