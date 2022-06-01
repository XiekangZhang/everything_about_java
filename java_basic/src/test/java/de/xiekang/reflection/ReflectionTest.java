package de.xiekang.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {
    /**
     * reflection get fields
     */
    @Test
    void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
        Object person = new Person();
        // getDeclaredFields --> all
        List<Field> fields = Arrays.asList(person.getClass().getDeclaredFields());
        final List<String> actualFieldNames = new ArrayList<String>();
        fields.forEach(field -> actualFieldNames.add(field.getName()));

        assertTrue(Arrays.asList("name", "age").containsAll(actualFieldNames));

        // getFields --> public fields
        for (Field field: person.getClass().getFields()) {
            System.out.println(field.getName());
        }
    }

    /**
     * reflection get class
     */
    @Test
    void givenObject_whenGetsClassName_thenCorrect() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("de.xiekang.reflection.Goat", clazz.getName());
        assertEquals("de.xiekang.reflection.Goat", clazz.getCanonicalName());
    }

    /**
     * reflection get class
     * @throws ClassNotFoundException
     */
    @Test
    void givenClassName_whenCreatesObject_thenCorrect() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("de.xiekang.reflection.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("de.xiekang.reflection.Goat", clazz.getName());
        assertEquals("de.xiekang.reflection.Goat", clazz.getCanonicalName());
    }

    /**
     * reflection get modifiers
     * @throws ClassNotFoundException
     */
    @Test
    void givenClass_whenRecognisesModifiers_thenCorrect() throws ClassNotFoundException{
        Class<?> goatClass = Class.forName("de.xiekang.reflection.Goat");
        Class<?> animalClass = Class.forName("de.xiekang.reflection.Animal");

        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();

        assertTrue(Modifier.isPublic(goatMods));
        assertTrue(Modifier.isAbstract(animalMods));
        assertTrue(Modifier.isPublic(animalMods));
    }

    /**
     * reflection get package
     */
    @Test
    void givenClass_whenGetsPackageInfo_thenCorrect() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("de.xiekang.reflection.Goat");
        Package pkg = clazz.getPackage();
        System.out.println(pkg.getName());
        assertEquals("de.xiekang.reflection", pkg.getName());
    }

    /**
     * reflection get super class
     */
    @Test
    void givenClass_whenGetsSuperClass_thenCorrect() throws ClassNotFoundException{
        Class<?> clazz = Class.forName("de.xiekang.reflection.Goat");
        Class<?> superClass = clazz.getSuperclass();
        String str = "any string";

        assertEquals("Animal", superClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName());
    }

    /**
     * reflection get interface
     * @throws ClassNotFoundException
     */
    @Test
    void givenClass_whenGetsImplementedInterfaces_thenCorrect() throws ClassNotFoundException{
        Class<?> goatClass = Class.forName("de.xiekang.reflection.Goat");
        Class<?> animalClass = Class.forName("de.xiekang.reflection.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }

    /**
     * reflection get constructors
     * @throws ClassNotFoundException
     */
    @Test
    void givenClass_whenGetsConstructor_thenCorrect() throws ClassNotFoundException{
        Class<?> goatClass = Class.forName("de.xiekang.reflection.Goat");
        Constructor<?>[] constructors = goatClass.getConstructors();
        assertEquals(1, constructors.length);
        assertEquals("de.xiekang.reflection.Goat", constructors[0].getName());
    }

    /**
     * reflection get fields
     * @throws ClassNotFoundException
     */
    @Test
    void givenClass_whenGetsFields_thenCorrect() throws ClassNotFoundException{
        Class<?> animalClass = Class.forName("de.xiekang.reflection.Animal");
        List<Field> fields = Arrays.asList(animalClass.getDeclaredFields());
        List<String> actualFields = new ArrayList<>();
        fields.forEach(field -> actualFields.add(field.getName()));

        assertEquals(2, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }

    @Test
    void givenClass_whenGetsMethods_thenCorrect() throws ClassNotFoundException{
        Class<?> animalClass = Class.forName("de.xiekang.reflection.Animal");
        List<Method> methods = Arrays.asList(animalClass.getDeclaredMethods());
        List<String> actualMethods = new ArrayList<>();
        methods.forEach(method -> actualMethods.add(method.getName()));

        System.out.println(actualMethods);
        assertEquals(6, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("getName", "setName", "getSound", "getCATEGORY", "eats", "setCATEGORY")));
    }
}