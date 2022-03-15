package de.java8tutorial.Stream;

import de.java8tutorial.Model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamParallel {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(new Product(23, "potatoes", 10.14f),
                new Product(14, "orange", 7.98f),
                new Product(3, "bread", 1.24f));

        Stream<Product> streamOfCollection = productList.parallelStream();
        System.out.println(streamOfCollection.isParallel());
        System.out.println(streamOfCollection.map(product -> product.getPrice() * 12).anyMatch(price -> price > 20));

        // if the source of a stream is something other than a collection or an array
        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
        System.out.println(intStreamParallel.isParallel());

        IntStream intStreamSequential = intStreamParallel.sequential();
        System.out.println(intStreamSequential.isParallel());
    }
}
