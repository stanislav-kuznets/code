package ru.otus.skuznets;

import ru.otus.skuznets.Annotations.*;

public class TestClass {

    @Before
    public static String beforeString() {
        return "Before String";
    }

    @After
    public static String afterString() {
        return "After String";
    }

    @Test
    public static String testString() {
        return "TestString";
    }
}
