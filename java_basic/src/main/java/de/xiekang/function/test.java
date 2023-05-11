package de.xiekang.function;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class test {
    public static void main(String[] args) {
        // INFO: standard function interface
        Map<String, Integer> nameMap = new HashMap<>();
        Integer value = nameMap.computeIfAbsent("John",
                String::length);
        System.out.println(value);

        // INFO: compose method that allows us to combine several functions into one and execute them sequentially
        Function<Integer, String> intToString = Objects::toString;
        Function<String, String> quote = s -> "'" + s + "'";
        Function<Integer, String> quoteIntToString = quote.compose(intToString);
        System.out.println(quoteIntToString.apply(5));
    }
}
