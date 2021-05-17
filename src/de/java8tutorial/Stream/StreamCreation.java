package de.java8tutorial.Stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) {
        // create stream
        Stream<String> streamEmpty = Stream.empty();

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        Stream<String> streaOfArray = Stream.of("a", "b", "c");

        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        // Arrays.stream(arr, from, to)
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        // generate --> an infinite stream
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

        // iterate --> an infinite stream
        Stream<Integer> streamIterated = Stream.iterate(40, integer -> integer + 2).limit(20);

        // stream of primitives
        IntStream intStream = IntStream.range(1, 3);
        LongStream longStream = LongStream.rangeClosed(1, 3);
        DoubleStream doubleStream = new Random().doubles(3);

        // stream of String
        IntStream streamOfChars = "abc".chars();
        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

        // stream of File
        Path path = Paths.get("C:\\file.txt");
        try {
            Stream<String> streamOfStrings = Files.lines(path);
            Stream<String> streamWithCharset = Files.lines(path, StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }
}
