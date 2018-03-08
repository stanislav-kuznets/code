package ru.otus.skuznets;

import static java.util.Collections.*;

public class Main {
    public static void main (String[] args) {
        MyArrayList<String> myList = new MyArrayList<String>();
        addAll(myList, "1", "2", "3");
        System.out.println(myList);

        MyArrayList<String> myList2 = new MyArrayList<String>();
        addAll(myList2, "A", "B", "C");
        copy(myList, myList2);
        System.out.println(myList2);

        sort(myList2);
        System.out.println(myList2);
    }
}
