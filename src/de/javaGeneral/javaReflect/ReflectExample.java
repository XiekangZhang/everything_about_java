package de.javaGeneral.javaReflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Java reflection allows us to inspect or/and modify runtime attributes of classes,
 * interfaces, fields and methods. This particularly comes in handy when we don't know their names at compile time.
 * Additionally, we can instantiate new objects, invoke methods, and get or set field values using reflection.
 * <p>
 * Reflect gives us access to the internal details of any object
 */

public class ReflectExample {

    private static List<String> getFieldName(Field[] fields) {
        //info: this can be used for name mapping --> Beans and DB Name
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    public static void main(String[] args) {
        Object person = new Person();
        Field[] fields = person.getClass().getDeclaredFields();

        List<String> actualFieldName = getFieldName(fields);
        System.out.println(actualFieldName);
        System.out.println("----------------------");

        // get the name of an object from the class
        Goat goat = new Goat("goat");
        Class<?> clazz = goat.getClass();
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());
        System.out.println("----------------------");

        // info: create an object if we only know its fully qualified class name
        try {
            Class<?> claz = Class.forName("de.javaGeneral.javaReflect.Goat");
            System.out.println(claz.getCanonicalName());
            System.out.println(claz.getSimpleName());
            System.out.println(claz.getName());
            System.out.println("----------------------");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        // class modifiers
        try {
            Class<?> goatClass = Class.forName("de.javaGeneral.javaReflect.Goat");
            Class<?> animalClass = Class.forName("de.javaGeneral.javaReflect.Animal");

            int goatMods = goatClass.getModifiers();
            int animalMods = animalClass.getModifiers();

            System.out.println(Modifier.isPublic(goatMods));
            System.out.println(Modifier.isAbstract(animalMods));
            System.out.println(Modifier.isPublic(animalMods));
            System.out.println("----------------------");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        // package information
        Package pkg = clazz.getPackage();
        System.out.println(pkg);
        System.out.println("----------------------");

        // Super class
        Class<?> goatSuperClass = clazz.getSuperclass();
        System.out.println(goatSuperClass.getSimpleName());
        System.out.println(String.class.getSuperclass().getSimpleName());
        System.out.println("----------------------");

        // interfaces
        Class<?>[] goatInterfaces = clazz.getInterfaces();
        System.out.println(goatInterfaces[0].getSimpleName());
        System.out.println("----------------------");

    }
}
