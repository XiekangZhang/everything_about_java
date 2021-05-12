package de.javaGeneral.javaGenerics;

public class MyGen<T> {
    T obj;

    void add(T obj) {
        this.obj = obj;
    }

    T getObj() {
        return obj;
    }
}
