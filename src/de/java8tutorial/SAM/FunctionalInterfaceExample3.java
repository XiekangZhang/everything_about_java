package de.java8tutorial.SAM;

interface Doable {
    default void doIt() {
        System.out.println("Do it now");
    }
}

@FunctionalInterface
interface Sayable extends Doable {
    void say(String msg);
}

public class FunctionalInterfaceExample3 implements Sayable {
    public void say(String msg) {
        System.out.println(msg);
    }
    public static void main(String[] args) {
        FunctionalInterfaceExample3 functionalInterfaceExample3 = new FunctionalInterfaceExample3();
        functionalInterfaceExample3.say("Hello there");
        functionalInterfaceExample3.doIt();
    }
}
