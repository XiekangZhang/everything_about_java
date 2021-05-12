package de.javaGeneral.javaGenerics.Shape;

public class Rectangle extends Shape{
    @Override
    void draw() {
        System.out.println("drawing " + this.getClass().getSimpleName());
    }
}
