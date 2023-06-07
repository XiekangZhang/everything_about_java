package de.xiekang.server.repository;

import de.xiekang.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    Employee getEmployee(int id);

    Employee updateEmployee(int id, String name);

    boolean deleteEmployee(int id);

    Employee addEmployee(int id, String name);

    int count();
}
