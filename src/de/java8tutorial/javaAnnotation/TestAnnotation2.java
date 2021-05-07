package de.java8tutorial.javaAnnotation;

import java.util.ArrayList;
import java.util.List;

public class TestAnnotation2 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("sonoo");
        list.add("vimal");
        list.add("ratan");

        for (Object obj: list) {
            System.out.println(obj);
        }
    }
}
