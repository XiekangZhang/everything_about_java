package de.javaGeneral.Generics;

public class MyGen<T> {
    T obj;

    void add(T obj) {
        this.obj = obj;
    }

    T getObj() {
        return obj;
    }
}
