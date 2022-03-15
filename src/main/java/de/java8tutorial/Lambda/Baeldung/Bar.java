package de.java8tutorial.Lambda.Baeldung;

@FunctionalInterface
public interface Bar {
    String method(String string);

    default String defaultCommon() {
        return "Common from Bar";
    }

    default String defaultBar() {
        return "Bar";
    }
}
