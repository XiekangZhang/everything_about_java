package de.java8tutorial.MethodRef;

import java.util.function.BiFunction;

class Arithmetic2 {
    public int add(int a, int b) {
        return a + b;
    }
}

public class InstanceMethodReference3 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> adder = new Arithmetic2()::add;
        System.out.println(adder.apply(10, 20));
    }
}
