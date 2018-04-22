package ru.otus.skuznets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ATMDepartment {

    private static ATMDepartment atmDepartment;
    private List<Notifier> observers = new ArrayList<>();

    public static ATMDepartment getAtmDepartment() {
        if(atmDepartment == null) {
            return new ATMDepartment();
        } else {
            return atmDepartment;
        }
    }

    public void add(Notifier notifier) {
        observers.add(notifier);
    }

    public void notify(Event event) {
        observers.forEach(notifier -> notifier.notify(event));
    }

    public int getTotalBalance() {
        return observers.stream().mapToInt(notifier -> ((ATM)notifier).getBalance()).sum();
    }

    public void restoreAllATM() {
        notify(new RestoreAllATMEvent());
    }
}
