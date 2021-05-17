package de.java8tutorial.Stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// warning: Java 8 Streams can't be reused
// warning: you have to cast it to collection type to refer it
public class StreamReference {

    public static void main(String[] args) {
        List<String> elements = Stream.of("a", "b", "c").filter(element -> !element.contains("b"))
                .collect(Collectors.toList());
        Optional<String> anyElement = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();
        System.out.println(anyElement + "\n" + firstElement);
    }
}
