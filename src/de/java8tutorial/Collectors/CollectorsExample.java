package de.java8tutorial.Collectors;

import de.java8tutorial.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsExample {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "HP Laptop", 25000f));
        productList.add(new Product(2, "Dell Laptop", 30000f));
        productList.add(new Product(3, "Lenovo Laptop", 28000f));
        productList.add(new Product(4, "Sony Laptop", 28000f));
        productList.add(new Product(5, "Apple Laptop", 90000f));

        // stream to list
        System.out.println(productList.stream()
                .map(product -> product.getPrice())
                .collect(Collectors.toList()));

        // stream to set
        System.out.println(productList.stream()
                .map(product -> product.getPrice())
                .collect(Collectors.toSet()));

        // sum
        System.out.println(
                (Double) productList.stream().mapToDouble(Product::getPrice).sum()
        );

        System.out.println(
                productList.stream().mapToInt(Product::getId).sum()
        );

        // average
        System.out.println(
                productList.stream().collect(Collectors.averagingDouble(Product::getPrice))
        );

        // counting
        System.out.println(
                (Long) (long) productList.size()
        );
    }
}
