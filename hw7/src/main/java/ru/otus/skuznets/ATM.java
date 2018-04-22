package ru.otus.skuznets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ATM implements Notifier{
    private Cell currentState;

    private List<Memento> savedStates = new ArrayList<Memento>();

    public ATM(List<Cell> cells) {
        Collections.sort(cells);
        Cell initialState = cells.get(0);
        linkCells(cells);
        savedStates.add(new Memento(initialState));
        currentState = initialState;
    }

    public void linkCells(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cell1 = iterator.next();
        while (iterator.hasNext()) {
            Cell cell2 = iterator.next();
            cell1.setNext(cell2);
            cell1 = cell2;
        }
    }

    public boolean withDraw(int requested) {
        return currentState.withDraw(requested);
    }

    public int getBalance() {
        return currentState.getBalance();
    }

    public void restoreATM() {
        currentState = savedStates.get(0).getInitiaState();
    }

    public void notify(Event event) {
        event.execute(this);
    }
}
