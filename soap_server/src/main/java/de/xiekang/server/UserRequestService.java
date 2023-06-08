package de.xiekang.server;

import de.xiekang.model.UserRequest;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.ByteArrayInputStream;

@WebService
public class UserRequestService {
    @WebMethod
    public String getUserRequestInfo(UserRequest userRequest) {
        return "id: " + userRequest.getId() + ", name: " + userRequest.getName();
    }
}
