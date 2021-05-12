package de.javaGeneral.javaGenerics.Shape;

public class Circle extends Shape{
    @Override
    void draw() {
        System.out.println("drawing " + this.getClass().getName());
    }
}
