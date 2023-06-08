package de.xiekang.client;


import java.net.MalformedURLException;
import java.net.URL;

public class UserRequestClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/user?wsdl");
        UserRequestService userRequestService = new UserRequestServiceService(url).getUserRequestServicePort();
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1);
        userRequest.setName("my_name");
        System.out.println(userRequestService.getUserRequestInfo(userRequest));
    }
}
