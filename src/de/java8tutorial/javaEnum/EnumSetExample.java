package de.java8tutorial.javaEnum;

import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetExample {

    enum Days {
        Sunday,
        Monday,
        Tuesday,
        Wednesday,
        Thursday
    }

    public static void main(String[] args) {
        EnumSet<Days> days = EnumSet.of(Days.Monday, Days.Tuesday);
        // create a EnumSet with the specified elements
        Iterator<Days> iterator = days.iterator();
        System.out.println("EnumSet: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
