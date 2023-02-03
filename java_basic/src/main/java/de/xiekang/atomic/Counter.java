package de.xiekang.atomic;

public class Counter {
    //IDEA: volatile --> ensure proper reference visibility
    private volatile int counter;

    //IDEA: synchronized --> one thread can enter the method at one time
    public synchronized void increment() {
        counter++;
    }
}