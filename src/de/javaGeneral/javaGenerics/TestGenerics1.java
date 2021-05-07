package de.javaGeneral.javaGenerics;

import java.util.ArrayList;

public class TestGenerics1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("rahul");
        list.add("jai");

        String s = list.get(1);

        list.stream().forEach(System.out::println);
    }
}
