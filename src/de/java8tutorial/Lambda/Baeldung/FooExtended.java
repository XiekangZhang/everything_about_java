package de.java8tutorial.Lambda.Baeldung;

@FunctionalInterface
public interface FooExtended extends Bar, Baz {
    @Override
    default String defaultCommon() {
        return Bar.super.defaultCommon();
    }
}
