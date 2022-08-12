package de.xiekang.serialization.gson_tutorial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.xiekang.serialization.gson_tutorial.Person;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class GsonTest {

    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.setName("bbblalskdfjl");
        person.setAge(10);
        String filePath = "C:\\Users\\xzhang\\Documents\\workspace\\everything_about_java\\java_basic\\src\\main\\java\\de\\xiekang\\serialization\\gson_tutorial\\person.json";


        Writer writer = new FileWriter(filePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(person.getName() + ": " + person.getAge());
        gson.toJson(person, writer);
        writer.flush();
        writer.close();


        Person person1 = gson.fromJson(new FileReader(filePath), Person.class);
        System.out.println(person1.getName() + ": " + person1.getAge());
    }
}
