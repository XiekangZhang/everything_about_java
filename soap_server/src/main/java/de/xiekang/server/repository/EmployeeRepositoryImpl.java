package de.xiekang.server.repository;

import de.xiekang.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private List<Employee> employeeList;

    public EmployeeRepositoryImpl() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Jane"));
        employeeList.add(new Employee(2, "Jack"));
        employeeList.add(new Employee(3, "George"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public Employee getEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new RuntimeException("Employee not found!");
    }

    @Override
    public Employee updateEmployee(int id, String name) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employee.setId(id);
                employee.setFirstName(name);
                return employee;
            }
        }
        throw new RuntimeException("Employee not found!");
    }

    @Override
    public boolean deleteEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeList.remove(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee addEmployee(int id, String name) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                throw new RuntimeException("Employee exists!");
            }
        }
        Employee employee = new Employee(id, name);
        employeeList.add(employee);
        return employee;
    }

    @Override
    public int count() {
        return employeeList.size();
    }
}
