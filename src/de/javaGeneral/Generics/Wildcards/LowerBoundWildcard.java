package de.javaGeneral.Generics.Wildcards;

import java.util.Arrays;
import java.util.List;

public class LowerBoundWildcard {
    public static void addNumbers(List<? super Integer> list) {
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 3);
        System.out.println("displaying the Integer values");
        addNumbers(l1);

        List<Number> l2 = Arrays.asList(1.0, 2.0, 3.0);
        System.out.println("displaying the Number values");
        addNumbers(l2);
    }
}
