package de.java8tutorial.Optional;

import java.util.Optional;

public class OptionalExample1 {
    public static void main(String[] args) {
        String[] str = new String[10];
        str[5] = "JAVA OPTIONAL CLASS EXAMPLE";
        Optional<String> checkNull = Optional.ofNullable(str[5]);
        if (checkNull.isPresent()) {
            System.out.println(str[5].toLowerCase());
        } else {
            System.out.println("string value is not present");
        }

        checkNull.ifPresent(System.out::println);   // printing value by using method reference
        System.out.println(checkNull.get());    // printing value by using get method
        System.out.println(str[5].toLowerCase());
    }
}
