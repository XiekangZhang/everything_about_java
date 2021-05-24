package de.javaGeneral.ThreadExp;

public class WithAlive implements Runnable {
    public static int amount = 0;

    public static void main(String[] args) {
        WithAlive withAlive = new WithAlive();
        Thread thread = new Thread(withAlive);
        thread.start();
        // Wait for the thread to finish
        while (thread.isAlive()) {
            System.out.println("Waiting...");
        }
        System.out.println("Main: " + amount);
        amount++;
        System.out.println("Main: " + amount);
    }

    public void run() {
        amount++;
    }
}
