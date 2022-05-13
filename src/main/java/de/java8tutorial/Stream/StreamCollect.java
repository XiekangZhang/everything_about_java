package de.java8tutorial.Stream;

import de.java8tutorial.Model.Product;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamCollect {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(new Product(23, "potatoes", 10.14f),
                new Product(14, "orange", 7.98f),
                new Product(3, "bread", 1.24f));

        List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
        System.out.println(collectorCollection);

        String listToString = productList.stream().map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(listToString);

        double averagePrice = productList.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.println(averagePrice);

        int summingPrice = productList.stream().collect(Collectors.summingInt(Product::getId));
        System.out.println(summingPrice);

        IntSummaryStatistics statistics = productList.stream()
                .collect(Collectors.summarizingInt(Product::getId));
        System.out.println(statistics);

        Map<Float, List<Product>> collectorMapOfLists = productList.stream()
                .collect(Collectors.groupingBy(Product::getPrice));
        System.out.println(collectorMapOfLists);

        Map<Boolean, List<Product>> mapPartitioned = productList.stream()
                .collect(Collectors.partitioningBy(element -> element.getPrice() > 5));
        System.out.println(mapPartitioned);

        Set<Product> unmodifiableSet = productList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        System.out.println(unmodifiableSet);

        // Custom collector
        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });
        LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
        System.out.println(linkedListOfPersons.get(1).getName());
    }
}
