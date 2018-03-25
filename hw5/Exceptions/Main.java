package ru.otus.skuznets;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            TestFramework.runTest(TestClass.class);
            TestFramework.runTest("ru.otus.skuznets");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
