package de.java8tutorial.Stream;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamReduction {
    public static void main(String[] args) {
        // accumulator is expensive, since it creates a new value for every step of reducing
        OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);
        System.out.println(reduced);

        // identity: the initial value for an accumulator
        int reduced1 = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        System.out.println(reduced1);

        // combiner: a function which aggregates the results of the accumulator
        int reduce2 = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reduce2);

        // parallelStream: 11, 12, 13 -> 11 + 12 + 13 = 36
        int reduce3 = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reduce3);
    }
}
