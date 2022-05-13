package de.javaGeneral.Generics;

import java.util.HashMap;
import java.util.Map;

public class TestGenerics2 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "vijay");
        map.put(4, "umesh");
        map.put(2, "ankit");

        map.entrySet().stream().forEach(integerStringEntry -> System.out.println("Key: " + integerStringEntry.getKey() +
                " Value: " + integerStringEntry.getValue()));
    }
}
