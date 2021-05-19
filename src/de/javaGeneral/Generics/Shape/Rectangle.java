package de.javaGeneral.Generics.Shape;

public class Rectangle extends Shape{
    @Override
    void draw() {
        System.out.println("drawing " + this.getClass().getSimpleName());
    }
}
