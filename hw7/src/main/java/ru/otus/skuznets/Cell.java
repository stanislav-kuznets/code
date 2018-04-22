package ru.otus.skuznets;

import java.util.Iterator;

public class Cell implements Comparable<Cell>, Iterable<Cell> {

    private int nominal;
    private int count;
    private Cell next;

    public Cell(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    int getBalance() {
        return count * nominal + (next != null ? next.getBalance() : 0);
    }

    public boolean withDraw(int requested) {
        int expectedCount = Math.min(requested / nominal, count);
        int expectedCash = expectedCount * nominal;
        boolean nextCellResult = true;
        if (requested != expectedCash) {
            nextCellResult = next != null && next.withDraw(requested - expectedCash);
        }
        if (nextCellResult) {
            count = count - expectedCount;
            return true;
        }
        return false;
    }

    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            Cell current = Cell.this;
            public boolean hasNext() {
                return current != null;
            }
            public Cell next() {
                Cell before = current;
                current = current.next;
                return before;
            }
        };
    }
    @Override
    public int compareTo(Cell o) {
        if (nominal > o.getNominal())
            return -1;
        if (nominal < o.getNominal())
            return 1;
        return 0;
    }
}
