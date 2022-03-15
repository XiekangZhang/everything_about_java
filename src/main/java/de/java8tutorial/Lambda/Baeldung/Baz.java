package de.java8tutorial.Lambda.Baeldung;

@FunctionalInterface
public interface Baz {
    String method(String string);

    default String defaultCommon() {
        return "Common from Baz";
    }

    default String defaultBaz() {
        return "Baz";
    }
}
