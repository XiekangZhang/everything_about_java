package de.javaGeneral.Generics;

public class MyGen<T> {
    T obj;

    void add(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public T newInstance(T obj) throws InstantiationException, IllegalAccessException {
        this.obj = obj;
        return this.obj;
    }
}
