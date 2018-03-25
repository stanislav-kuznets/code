package ru.otus.skuznets.Exceptions;

public class MoreOneAfterAnnotationException extends Exception {

    public MoreOneAfterAnnotationException() {
        super("Больше одной аннотации After");
    }
}