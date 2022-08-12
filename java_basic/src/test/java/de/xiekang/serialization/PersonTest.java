package de.xiekang.serialization;

import de.xiekang.serialization.defaultNative.Person;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame() throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge(20);
        person.setName("Joe");

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\xzhang\\Documents\\workspace\\everything_about_java\\java_basic\\src\\main\\java\\de\\xiekang\\serialization\\defaultNative\\yourfile.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\xzhang\\Documents\\workspace\\everything_about_java\\java_basic\\src\\main\\java\\de\\xiekang\\serialization\\defaultNative\\yourfile.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person p2 = (Person) objectInputStream.readObject();
        objectInputStream.close();

        assertTrue(p2.getAge() == person.getAge());
        assertTrue(p2.getName().equals(person.getName()));
    }

}