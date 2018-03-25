package ru.otus.skuznets.Exceptions;

public class MoreOneBeforeAnnotationException extends Exception {
    public MoreOneBeforeAnnotationException() {
        super("Больше одной аннотации Before.");
    }
}
