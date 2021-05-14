package de.java8tutorial.Stream;

import java.util.ArrayList;
import java.util.List;

public class ForEachOrderedExample {
    public static void main(String[] args) {
        List<String> gamesList = new ArrayList<>();
        gamesList.add("Football");
        gamesList.add("Cricket");
        gamesList.add("Chess");
        gamesList.add("Hockey");
        gamesList.stream().forEach(System.out::println);
        gamesList.stream().forEachOrdered(System.out::println);
    }
}
