package de.java8tutorial.Enum;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapExample {

    public enum Fruits {
        Apple,
        Mango,
        Orange,
        Banana
    }

    public static void main(String[] args) {
        EnumMap<Fruits, Integer> enumMap = new EnumMap<>(Fruits.class);
        // EnumMap(Class<K> keyType): creates an empty enum map with the specified key type

        enumMap.put(Fruits.Apple, 1);
        enumMap.put(Fruits.Mango, 2);
        enumMap.put(Fruits.Orange, 3);
        enumMap.put(Fruits.Banana, 4);

        System.out.println("Size of EnumMap: " + enumMap.size());
        for (Map.Entry m: enumMap.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }
}
