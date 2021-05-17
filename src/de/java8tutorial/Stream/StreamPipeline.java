package de.java8tutorial.Stream;

import java.util.Arrays;
import java.util.List;

public class StreamPipeline {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1).map(element -> element.substring(0, 3)).sorted().count();
        System.out.println(size);
    }
}
