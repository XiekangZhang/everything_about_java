package de.java8tutorial.Lambda.Baeldung;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        //Foo foo = parameter -> parameter + " from lambda";
        UseFoo useFoo = new UseFoo();
        Function<String, String> fn = parameter -> parameter + " from lambda";
        String result = useFoo.add("Message", fn);

        System.out.println(result);
    }
}
