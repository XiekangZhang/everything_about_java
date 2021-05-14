package de.java8tutorial.StringJoiner;

import java.util.StringJoiner;

public class StringJoinerExample {

    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner("*** ");
        StringJoiner joiner1 = new StringJoiner(", ", "[", "]");

        joiner.add("Rahul");
        joiner.add("Raju");
        joiner.add("Peter");
        joiner.add("Raheem");
        System.out.println(joiner);

        joiner1.add("Rahul");
        joiner1.add("Raju");
        joiner1.add("Peter");
        joiner1.add("Raheem");
        System.out.println(joiner1);

        System.out.println(joiner1.merge(joiner));
    }
}
