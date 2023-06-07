package de.xiekang.server;

import de.xiekang.server.endpoint.EmployeeServiceImpl;
import jakarta.xml.ws.Endpoint;

public class EmployeeServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/employeeservice", new EmployeeServiceImpl());
    }
}
