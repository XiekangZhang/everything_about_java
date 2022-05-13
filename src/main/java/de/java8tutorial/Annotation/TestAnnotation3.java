package de.java8tutorial.Annotation;

class A {
    void m() {
        System.out.println("hello m");
    }

    @Deprecated
    void n() {
        System.out.println("hello n");
    }
}

public class TestAnnotation3 {
    public static void main(String[] args) {
        A a = new A();
        a.n();
    }
}
