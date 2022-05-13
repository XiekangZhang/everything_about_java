package de.java8tutorial.SAM;

@FunctionalInterface
interface sayable {
    void say(String msg);

    static void test() {

    }

    default void test1() {
    }
    // * methods from object class
    int hashCode();
    String toString();
    boolean equals(Object obj);
}

public class FunctionalInterfaceExample implements sayable{
    @Override
    public void say(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        FunctionalInterfaceExample functionalInterfaceExample = new FunctionalInterfaceExample();
        functionalInterfaceExample.say("Hello there");
    }
}
