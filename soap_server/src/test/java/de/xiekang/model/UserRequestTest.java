package de.xiekang.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    @Test
    void marshal() throws DatatypeConfigurationException, JAXBException {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(1);
        userResponse.setName("test");
        userResponse.setGender("male");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        userResponse.setCreated(calendar);
        JAXBContext context = JAXBContext.newInstance(userResponse.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(userResponse, new File("./src/main/resources/userReponse.xml"));
    }

    @Test
    void unmarshal() throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserResponse.class);
        UserResponse userResponse = (UserResponse) jaxbContext.createUnmarshaller().unmarshal(
                new FileReader("./src/main/resources/userReponse.xml"));
        assertAll("userResponse deserialization",
                () -> assertEquals(userResponse.getId(), 1),
                () -> assertEquals(userResponse.getName(), "test"));
    }
}