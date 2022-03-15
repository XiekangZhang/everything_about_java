package de.java8tutorial.Default;

interface Sayable {
    default void say() {
        System.out.println("Hello, this is default method");
    }

    static void sayLouder(String msg) {
        System.out.println(msg);
    }

    void sayMore(String msg);
}

public class DefaultsMethods implements Sayable {
    @Override
    public void sayMore(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        DefaultsMethods defaultsMethods = new DefaultsMethods();
        Sayable sayable = defaultsMethods::sayMore;

        defaultsMethods.say();
        defaultsMethods.sayMore("Work is worship");
        sayable.sayMore("This is a method reference!");
        Sayable.sayLouder("This is a static method in an interface");
    }
}
