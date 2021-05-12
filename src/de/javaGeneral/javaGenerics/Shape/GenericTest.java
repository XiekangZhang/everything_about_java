package de.javaGeneral.javaGenerics.Shape;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {
    public static void drawShapes(List<? extends Shape> lists) {
        for (Shape shape: lists) {
            shape.draw();
        }
    }
    public static void main(String[] args) {
        List<Rectangle> list1 = new ArrayList<>();
        list1.add(new Rectangle());

        List<Circle> list2 = new ArrayList<>();
        list2.add(new Circle());
        list2.add(new Circle());

        drawShapes(list1);
        drawShapes(list2);
    }
}
