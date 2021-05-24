package de.javaGeneral.ThreadExp;

public class WithoutAlive extends Thread {
    public static int amount = 0;

    public static void main(String[] args) {
        WithoutAlive thread = new WithoutAlive();
        thread.start();
        System.out.println(amount);
        amount++;
        System.out.println(amount);
    }

    public void run() {
        amount++;
    }
}
