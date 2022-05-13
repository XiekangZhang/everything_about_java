package de.java8tutorial.Lambda.Baeldung;

import java.util.function.Function;

public class UseFoo {
    public String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }
}
