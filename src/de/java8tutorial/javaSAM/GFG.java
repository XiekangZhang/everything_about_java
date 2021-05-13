package de.java8tutorial.javaSAM;

import java.util.function.BinaryOperator;

public class GFG {
    public static void main(String[] args) {
        BinaryOperator<Integer> op = BinaryOperator.maxBy((a, b) -> (a > b) ? 1 : ((a.equals(b)) ? 0 : -1));
        System.out.println(op.apply(98, 11));
    }
}
