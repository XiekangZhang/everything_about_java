package de.javaGeneral.javaGenerics;

import java.util.Arrays;

public class TestGenerics4 {
    public static <E> void printArray(E[] elements) {
        Arrays.stream(elements).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Integer[] integers = {10, 20, 30, 40, 50};
        Character[] characters = {'J', 'A', 'V', 'A', 'P', 'O', 'I',  'N', 'T'};

        System.out.println("Printing Integer Array");
        printArray(integers);

        System.out.println("Printing Character Array");
        printArray(characters);
    }
}
