package ru.otus.skuznets;

import java.util.ArrayList;
import java.util.List;

import java.lang.management.ManagementFactory;
import java.util.List;

public class Calc {

    public static long getMemorySize() throws InterruptedException{
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static long getStringSize(int size) throws InterruptedException {
        long memoryBefore = getMemorySize();
        String[] s = new String[size];
        for(int i = 0; i< size; i++) {
            s[i] = new String(new char[0]);
        }
        long memoryAfter = getMemorySize();
        return (memoryAfter - memoryBefore) / s.length;
    }

    public static long getObjectSize(int size) throws InterruptedException {
        long memoryBefore = getMemorySize();
        Object[] o = new Object[size];
        for(int i=0; i < size; i++) {
            o[i] = new Object();
        }
        long memoryAfter = getMemorySize();
        return (memoryAfter - memoryBefore) / o.length;
    }

    public static long getListSize(int size) throws InterruptedException {
        long memoryBefore = getMemorySize();
        List[] l = new List[size];
        for (int i = 0; i < size; i++) {
            l[i] = new ArrayList();
        }
        long memoryAfter = getMemorySize();
        return (memoryAfter - memoryBefore) / l.length;
    }

    public static long getArrayListSize(int size) throws InterruptedException {
        long memoryBefore = getMemorySize();
        List list = new ArrayList();
        int l = 0;
        for(int i = 0; i < size; i++) {
            list.add(l++);
        }
        long memoryAfter = getMemorySize();
        return memoryAfter - memoryBefore;
    }
}