package de.java8tutorial.javaMethodRef;

interface Sayable {
    void say();
}

public class MethodReference {
    public static void saySomething() {
        System.out.println("Hello, this is static method.");
    }

    public static void main(String[] args) {
        Sayable sayable = MethodReference::saySomething;
        sayable.say();

        Sayable sayable1 = () -> MethodReference.saySomething();
        sayable1.say();
    }
}
