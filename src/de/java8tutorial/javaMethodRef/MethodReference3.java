package de.java8tutorial.javaMethodRef;

import java.util.function.BiFunction;

class Arithmetic {
    public static int add(int a, int b) {
        return a + b;
    }
}


public class MethodReference3 {
    public static void main(String[] args) {
        // ! BiFunction takes two inputs and return one output
        BiFunction<Integer, Integer, Integer> adder = Arithmetic::add;
        int result = adder.apply(10, 20);
        System.out.println(result);
    }
}
