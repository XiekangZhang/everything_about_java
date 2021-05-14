package de.java8tutorial.Stream;

import de.java8tutorial.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamExample {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "HP Laptop", 25000f));
        productList.add(new Product(2, "Dell Laptop", 30000f));
        productList.add(new Product(3, "Lenovo Laptop", 28000f));
        productList.add(new Product(4, "Sony Laptop", 28000f));
        productList.add(new Product(5, "Apple Laptop", 90000f));
        List<Float> productPriceList = new ArrayList<>();

        /*for (Product product: productList) {
            if (product.price < 30000) {
                productPriceList.add(product.price);
            }
        }*/

        productList.stream().filter(product -> product.price > 30000) // filtering data
                .map(product -> product.price) // fetching price
                .collect(Collectors.toList())
                .forEach(System.out::println); // collecting as list

        // System.out.println(productPriceList);

        Stream.iterate(1, element -> element + 1).
                filter(element -> element % 5 == 0).limit(5).
                forEach(System.out::println);

        productList.stream()
                .filter(product -> product.price == 30000)
                .forEach(product -> System.out.println(product.name));

        float totalPrice = productList.stream()
                .map(product -> product.price)
                .reduce(0.0f, Float::sum);
        System.out.println(totalPrice);

        /*
        double totalPrice1 = productList.stream()
                .collect(Collectors.summingDouble(product -> product.price));
         */
        double totalPrice1 = productList.stream().mapToDouble(product -> product.price).sum();
        System.out.println(totalPrice1);

        Product productMax = productList.stream()
                .max((product1, product2) -> product1.price > product2.price ? 1 : -1).get();

        Product productMin = productList.stream()
                .max((product1, product2) -> product1.price < product2.price ? 1 : -1).get();

        System.out.println(productMax.price + " " + productMin.price);

        long count = productList.stream()
                .filter(product -> product.price < 30000)
                .count();
        System.out.println(count);

        productList.stream()
                .filter(product -> product.price < 30000)
                .map(Product::getPrice)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        // Converting Product List into a Map
        Map<Integer, String> productPriceMap = productList.stream()
                .collect(Collectors.toMap(product -> product.id, product -> product.name));
        System.out.println(productPriceMap);
    }
}
