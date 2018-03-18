package ru.otus.skuznets;

import java.util.ArrayList;
import java.util.List;

public class FillArray extends Thread {

    private List<String> list = new ArrayList<String>();
    private static int arrayValue = 500000;
    private static int removeValue = 250000;

    FillArray() {
        start();
    }

    public void run() {

        while (true) {
            try {
                for (int i = 0; i < arrayValue; arrayValue++) {
                    list.add(new String());
                }
                System.out.println("list size with 500000 elements: " + list.size());
                for (int j = 0; j < removeValue; removeValue++) {
                    list.remove(list.size() - 1);
                }
                System.out.println("list size after remove: " + list.size());

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
