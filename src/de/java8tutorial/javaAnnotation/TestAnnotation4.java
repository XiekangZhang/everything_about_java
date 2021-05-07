package de.java8tutorial.javaAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    int value1() default 1;
    String value2() default "";
    String value3() default "xyz";
}

@MyAnnotation(value1 = 10, value2 = "Xiekang", value3 = "Zhang")
public class TestAnnotation4 {

}
