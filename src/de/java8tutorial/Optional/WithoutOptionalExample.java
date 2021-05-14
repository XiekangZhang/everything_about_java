package de.java8tutorial.Optional;

public class WithoutOptionalExample {
    public static void main(String[] args) {
        String[] str = new String[10];
        String lowercaseString = str[5].toLowerCase();
        System.out.println(lowercaseString);
    }
}
