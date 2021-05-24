package de.javaGeneral.Tree;

import java.util.Comparator;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * It stores unique elements.
 * It doesn't preserve the insertion order of the elements
 * It sorts the elements in ascending order
 * It's not thread-safe
 *
 * TreeSet relates  with TreeMap
 */
public class TreeSetExample {
    // info: TreeSet uses a self-balancing binary search tree, a specified version of red-black tree

    public static void main(String[] args) {
        Set<String> treeSet = new TreeSet<>(Comparator.comparing(String::length)); // you can add an additional function into TreeSet
        treeSet.add("String added");
        System.out.println("contains a string: " + treeSet.contains("String added"));
        // info: remove(), clear(), size(), isEmpty(), iterator(), first(), last(), subSet(fromElement, toElement)
        //       headSet(index) = subSet(1, index), tailSet(index) = subSet(index, size())
        treeSet.forEach(System.out::println);
    }
}
