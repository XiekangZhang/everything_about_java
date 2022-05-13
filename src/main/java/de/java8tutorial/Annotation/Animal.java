package de.java8tutorial.Annotation;

public class Animal {
    void eatSomething() {
        System.out.println("eating something");
    }
}

class Dog extends Animal {
    @Override
    void eatSomething() {
        super.eatSomething();
    }
}

class TestAnnotation1 {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.eatSomething();
    }
}
