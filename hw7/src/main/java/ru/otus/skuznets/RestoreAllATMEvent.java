package ru.otus.skuznets;

public class RestoreAllATMEvent implements Event {
    public void execute (ATM atm) {
        atm.restoreATM();
    }

}
