package de.xiekang.basic;

import java.util.ArrayList;
import java.util.List;

public class MyArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach(System.out::println);
    }
}

