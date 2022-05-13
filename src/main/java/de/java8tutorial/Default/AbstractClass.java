package de.java8tutorial.Default;

abstract class AbstractClass {
    // constructor
    public AbstractClass() {
        System.out.println("You can create constructor in an abstract class");
    }

    // abstract method
    abstract int add(int a, int b);

    // non-static method
    int sub(int a, int b) {
        return a - b;
    }

    // static method
    static int multiply(int a, int b) {
        return a * b;
    }
}
