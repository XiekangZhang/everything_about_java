package de.javaGeneral.ThreadExp;

public class MainTest implements Runnable{
    public static void main(String[] args) {
        MainTest obj = new MainTest();
        Thread thread = new Thread(obj);
        thread.start();
        System.out.println("This code is outside of the thread");
    }
    public void run() {
        System.out.println("This code is running in a thread");
    }
}
