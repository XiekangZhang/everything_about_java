package de.java8tutorial.Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Product {
    int id;
    String name;
    float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class LambdaExpressionExample10 {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "HP Laptop", 25000f));
        list.add(new Product(3, "Keyboard", 300f));
        list.add(new Product(2, "Dell Mouse", 150f));

        System.out.println("Sorting on the basis of name...");

        Collections.sort(list, Comparator.comparing(p -> p.name));

        list.forEach(
                product -> System.out.println(product.id + " " + product.name + " " + product.price)
        );

        List<Product> list1=new ArrayList<>();
        list1.add(new Product(1,"Samsung A5",17000f));
        list1.add(new Product(3,"Iphone 6S",65000f));
        list1.add(new Product(2,"Sony Xperia",25000f));
        list1.add(new Product(4,"Nokia Lumia",15000f));
        list1.add(new Product(5,"Redmi4 ",26000f));
        list1.add(new Product(6,"Lenevo Vibe",19000f));

        Stream<Product> filtered_data = list1.stream().filter(product -> product.price > 20000);
        filtered_data.forEach(
                product -> System.out.println(product.name + ": " + product.price)
        );
    }
}