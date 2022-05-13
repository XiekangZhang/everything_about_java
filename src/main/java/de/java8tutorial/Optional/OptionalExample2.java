package de.java8tutorial.Optional;

import java.util.Optional;

public class OptionalExample2 {
    public static void main(String[] args) {
        String[] str = new String[10];
        str[5] = "JAVA OPTIONAL CLASS EXAMPLE";

        // it returns an empty instance of optional class
        Optional<String> empty = Optional.empty();
        System.out.println(empty);

        // it returns a non-empty optional
        Optional<String> value = Optional.of(str[5]);

        // if value is present, it returns an optional otherwise returns an empty optional
        System.out.println("Filtered value: " + value.filter(s -> s.equals("Abc")));
        System.out.println("Filtered value: " + value.filter(s -> s.equals("JAVA OPTIONAL CLASS EXAMPLE")));

        // it returns value of an Optional. if value is not present, it throws an NoSuchElementException
        System.out.println("Getting value: " + value.get());
        // System.out.println("Getting value: " + empty.get());

        // it returns hashcode of the value
        System.out.println("Getting hashcode: " + value.hashCode());

        // it returns true if value is present, otherwise false
        System.out.println("Is value present: " + value.isPresent());

        // it returns non-empty Optional if value is present, otherwise returns an empty optional
        System.out.println("Nullable Optional: " + Optional.ofNullable(str[5]));

        // it returns value if available, otherwise returns specified value
        System.out.println("orElse: " + value.orElse("Value is not present"));
        System.out.println("orElse: " + empty.orElse("Value is not present"));
        value.ifPresent(System.out::println);
    }
}
