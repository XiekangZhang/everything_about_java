package de.java8tutorial.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation1 {
    int value();
}

// Applying annotation
class Hello {
    @MyAnnotation1(value = 10)
    public void sayHello() {
        System.out.println("hello annotation");
    }
}

public class TestCustomAnnotation1 {
    public static void main(String[] args) throws NoSuchMethodException {
        Hello h = new Hello();
        Method m = h.getClass().getMethod("sayHello");

        MyAnnotation1 manno = m.getAnnotation(MyAnnotation1.class);
        System.out.println("value is " + manno.value());
    }
}
