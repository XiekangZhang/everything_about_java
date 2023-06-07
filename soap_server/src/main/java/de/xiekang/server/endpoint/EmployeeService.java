package de.xiekang.server.endpoint;

import de.xiekang.model.Employee;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

// @WebResult: customize name of the XML element that represents the return value
@WebService // it is a web service interface
public interface EmployeeService {
    @WebMethod // a web service operation
    Employee getEmployee(int id);

    @WebMethod
    Employee updateEmployee(int id, String name);

    @WebMethod
    boolean deleteEmployee(int id);

    @WebMethod
    Employee addEmployee(int id, String name);

    @WebMethod
    int countEmployees();

    @WebMethod
    List<Employee> getAllEmployees();
}
