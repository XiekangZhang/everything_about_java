package de.xiekang.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
        Object person = new Person();
        // getDeclaredFields --> all
        Field[] fields = person.getClass().getDeclaredFields();

        final List<String> actualFieldNames = new ArrayList<String>();
        for (Field field: fields) {
            actualFieldNames.add(field.getName());
        }

        assertTrue(Arrays.asList("name", "age").containsAll(actualFieldNames));

        // getFields --> public fields
        for (Field field: person.getClass().getFields()) {
            System.out.println(field.getName());
        }
    }
}