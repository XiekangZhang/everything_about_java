package de.javaGeneral.javaReflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BirdTest {
    public static void main(String[] args) {
        try {
            /**
             * Access to Constructors: getConstructor()
             * Initialization of an object: newInstance()
             */
            Class<?> birdClass = Class.forName("de.javaGeneral.javaReflect.Bird");
            Constructor<?>[] constructors = birdClass.getConstructors();
            System.out.println(constructors.length);
            // info: instantiate objects at runtime
            Constructor<?> cons1 = birdClass.getConstructor();
            Constructor<?> cons2 = birdClass.getConstructor(String.class);
            Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

            Bird bird1 = (Bird) cons1.newInstance();
            Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
            Bird bird3 = (Bird) cons3.newInstance("Dove", true);

            System.out.println("Constructor: " + bird1.getName());
            System.out.println("Constructor: " + bird2.getName());
            System.out.println("Constructor: " + bird3.isWalks());

            /**
             * Access to public fields from the class and superclasses: getFields() or getField(name)
             * Access to public, default, protected, private fields from the class: getDeclaredFields() or getDeclaredField(name)
             */
            // info: get and set fields values at runtime
            // info: return all accessible public fields class and superclasses
            Field[] fields = birdClass.getFields();
            System.out.println("Field: " + fields.length);
            System.out.println("Field: " + fields[0].getName());
            System.out.println("Field: " + birdClass.getField("CATEGORY").getName());

            // info: getDeclaredFields: the private fields in the class not in superclass
            Field[] fields1 = birdClass.getDeclaredFields();
            System.out.println("Field: " + fields1.length);
            System.out.println("Field: " + fields1[0].getName());
            System.out.println("Field: " + birdClass.getDeclaredField("walks").getName());

            /**
             * Access and modify the value of fields process:
             * - getField(name) or getDeclaredField(name)
             * - field.setAccessible(true)
             * - field.set(object, value)
             */
            Field field = birdClass.getDeclaredField("walks");
            Field field1 = birdClass.getField("CATEGORY");
            System.out.println("access to public static value by using null: " + field1.get(null));
            // info: setAccessible(true) to enable access and modify the field
            field.setAccessible(true);
            System.out.println("original value: " + bird1.isWalks());
            field.set(bird1, true);
            System.out.println("new value: " + bird1.isWalks());

            /**
             * Access to all public methods from class and superclasses: getMethods()
             * Access to all public, protected, private, default methods from class: getDeclaredMethods()
             * Other possibilities: getMethod(name) and getDeclaredMethod(name)
             */
            Method[] methods = birdClass.getMethods();
            Method[] methods1 = birdClass.getDeclaredMethods();
            Function<Method, String> getMethodName = method -> method.getName();
            List<String> methodNames = Arrays.stream(methods).map(getMethodName::apply).collect(Collectors.toList());
            List<String> method1Names = Arrays.stream(methods1).map(getMethodName::apply).collect(Collectors.toList());
            System.out.println("all public methods: " + methodNames);
            System.out.println("declared methods: " + method1Names);

            /**
             * method.invoke()
             */
            Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
            Method walksMethod = birdClass.getDeclaredMethod("isWalks");
            boolean walks = (boolean) walksMethod.invoke(bird1);
            System.out.println("can bird1 walk? " + walks);

            setWalksMethod.invoke(bird1, true);
            boolean walks2 = (boolean) walksMethod.invoke(bird1);
            System.out.println("can bird1 now walk?" + walks2);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException | NoSuchFieldException
                classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
