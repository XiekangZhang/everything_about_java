package de.xiekang.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BirdConstructorTest {

    @Test
    void givenClass_whenGetsAllConstructors_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Constructor<?>[] constructors = birdClass.getConstructors();
        assertEquals(3, constructors.length);
    }

    @Test
    void givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect() throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // info: instantiate object at runtime
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class, boolean.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class);

        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("dove", true);
        Bird bird3 = (Bird) cons3.newInstance("Weaver bird");

        assertEquals("bird", bird1.getName());
        assertEquals("dove", bird2.getName());
        assertEquals("Weaver bird", bird3.getName());

        assertFalse(bird1.walks());
        assertTrue(bird2.walks());
    }

    @Test
    void givenClass_whenGetsPublicFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Field[] fields = birdClass.getFields();

        assertEquals(1, fields.length);
        assertEquals("CATEGORY", fields[0].getName());
    }

    @Test
    void givenClass_whenGetsPublicFieldByName_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        // info: no possible to access private fields declared in superclasses
        // info: and not declared in the child class
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Field field = birdClass.getField("CATEGORY");

        assertEquals("CATEGORY", field.getName());
    }

    @Test
    void givenClass_whenGetsDeclaredFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Field[] fields = birdClass.getDeclaredFields();
        assertEquals(1, fields.length);
        assertEquals("walks", fields[0].getName());
    }

    @Test
    void givenClass_whenGetsFieldsByName_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Field field = birdClass.getDeclaredField("walks");

        assertEquals("walks", field.getName());
    }

    @Test
    void givenClassField_whenGetsType_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Field field = Class.forName("de.xiekang.reflection.Bird").getDeclaredField("walks");
        Class<?> fieldClass = field.getType();

        assertEquals("boolean", fieldClass.getSimpleName());
    }

    @Test
    void givenClassField_whenSetsAndGetsValue_thenCorrect() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // info: modify field object
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Field field = birdClass.getDeclaredField("walks");
        // warn: field set accessible, then set the value
        field.setAccessible(true);

        assertFalse(field.getBoolean(bird));
        assertFalse(bird.walks());

        field.set(bird, true);

        assertTrue(field.getBoolean(bird));
        assertTrue(bird.walks());
    }

    @Test
    void givenClassField_whenGetsAndSetsWithNull_thenCorrect() throws ClassNotFoundException,
            NoSuchFieldException, IllegalAccessException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Field field = birdClass.getField("CATEGORY");
        field.setAccessible(true);

        // info: field.get(null) --> default value of a static attribute
        assertEquals("domestic", field.get(null));
    }

    @Test
    void givenClass_whenGetsAllPublicMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        List<Method> methods = Arrays.asList(birdClass.getMethods());
        List<String> methodNames = new ArrayList<>();

        methods.forEach(method -> methodNames.add(method.getName()));

        assertTrue(methodNames.containsAll(Arrays.asList("equals", "notifyAll", "hashCode", "walks",
                "eats", "toString")));
    }

    @Test
    void givenClass_whenGetsOnlyDeclaredMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        List<Method> methods = Arrays.asList(birdClass.getDeclaredMethods());
        List<String> methodNames = new ArrayList<>();

        methods.forEach(method -> methodNames.add(method.getName()));
        System.out.println(methodNames);
        assertTrue(methodNames.containsAll(Arrays.asList("setWalks", "walks", "isWalks", "getSound")));
    }

    @Test
    void givenMethodName_whenGetsMethod_thenCorrect() throws Exception {
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Method walksMethod = birdClass.getDeclaredMethod("walks");
        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
    }

    @Test
    void givenMethod_whenInvokes_thenCorrect() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        // info: invoke a method at runtime
        Class<?> birdClass = Class.forName("de.xiekang.reflection.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        Method walksMethod = birdClass.getDeclaredMethod("walks");
        boolean walks = (boolean) walksMethod.invoke(bird);

        assertFalse(walks);
        assertFalse(bird.walks());

        setWalksMethod.invoke(bird, true);

        boolean walk2 = (boolean) walksMethod.invoke(bird);
        assertTrue(walk2);
        assertTrue(bird.walks());
    }
}