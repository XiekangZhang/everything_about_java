package de.javaGeneral.Generics.Wildcards;

import java.util.ArrayList;

public class UpperBoundWildcard {

    private static Double add(ArrayList<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }

    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(10);
        l1.add(20);
        System.out.println("displaying the sum = " + add(l1));

        ArrayList<Double> l2 = new ArrayList<>();
        l2.add(30.0);
        l2.add(40.0);
        System.out.println("displaying the sum = " + add(l2));
    }
}
