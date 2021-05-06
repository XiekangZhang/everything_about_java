package de.java8tutorial.javaLambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaExpressionExample7 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("ankit");
        list.add("mayank");
        list.add("irfan");
        list.add("jai");

        list.forEach(
                System.out::println
        );

        Map<Integer, String> namesMap = new HashMap<>();
        namesMap.put(1, "Larry");
        namesMap.put(2, "Steve");
        namesMap.put(3, "James");

        namesMap.forEach((key, value) -> System.out.println(key + " " + value));

        namesMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}
