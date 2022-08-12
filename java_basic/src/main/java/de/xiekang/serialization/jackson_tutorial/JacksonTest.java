package de.xiekang.serialization.jackson_tutorial;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.xiekang.serialization.gson_tutorial.Person;

import java.io.IOException;
import java.io.StringWriter;

public class JacksonTest {
    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.setName("jfskldjsldjflkalsdjfklsjfdlk");
        person.setAge(98);

        //String filePath = "C:\\Users\\xzhang\\Documents\\workspace\\everything_about_java\\java_basic\\src\\main\\java\\de\\xiekang\\serialization\\jackson_tutorial\\jackson_person.json";
        StringWriter stringWriter = new StringWriter();

        //File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(stringWriter, person);
        System.out.println(stringWriter);

        //Person person1 = mapper.readValue(new File(filePath), Person.class);
        //System.out.println(person1.getName() + ": " + person1.getAge());

        Person person1 = mapper.readValue(stringWriter.toString(), Person.class);
        System.out.println(person1.getName() + ": " + person1.getAge());
    }
}
