package de.java8tutorial.Default;

public class AbstractTest extends AbstractClass {
    @Override
    int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        AbstractTest a = new AbstractTest();
        int result1 = a.add(20, 10);
        int result2 = a.sub(20, 10);
        int result3 = AbstractClass.multiply(20, 10);
        System.out.println("Addition: " + result1);
        System.out.println("Subtraction: " + result2);
        System.out.println("Multiplication: " + result3);
    }
}
