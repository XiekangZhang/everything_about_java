package de.java8tutorial.Optional;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        String[] str = new String[10];
        Optional<String> checkNull = Optional.ofNullable(str[5]);
        if (checkNull.isPresent()) {
            System.out.println(str[5].toLowerCase());
        } else {
            System.out.println("string value is not present");
        }
    }
}
