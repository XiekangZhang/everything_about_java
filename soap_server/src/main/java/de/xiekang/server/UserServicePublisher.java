package de.xiekang.server;

import jakarta.xml.ws.Endpoint;

public class UserServicePublisher {
    public static void main(String[] args) {
        System.out.println("Publish server on http://localhost:8080/user");
        Endpoint.publish(
                "http://localhost:8080/user",
                new UserRequestService()
        );
    }
}
