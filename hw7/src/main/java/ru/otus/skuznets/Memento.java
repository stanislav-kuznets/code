package ru.otus.skuznets;

public class Memento {

    private Cell initialState;

    Memento(Cell stateToSave) {
        this.initialState = stateToSave;
    }

    Cell getInitiaState() {
        return initialState;
    }
}
