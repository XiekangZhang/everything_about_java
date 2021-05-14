package de.java8tutorial.MethodRef;

public class InstanceMethodReference {
    public void saySomething() {
        System.out.println("Hello, this is non-static method.");
    }

    public static void main(String[] args) {
        InstanceMethodReference methodReference = new InstanceMethodReference();
        Sayable sayable = methodReference::saySomething;
        sayable.say();

        Sayable sable1 = new InstanceMethodReference()::saySomething;
        sable1.say();
    }
}
