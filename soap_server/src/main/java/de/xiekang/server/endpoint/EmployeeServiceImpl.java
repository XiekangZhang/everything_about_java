package de.xiekang.server.endpoint;

import de.xiekang.model.Employee;
import de.xiekang.server.repository.EmployeeRepository;
import de.xiekang.server.repository.EmployeeRepositoryImpl;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.inject.Inject;

import java.util.List;

@WebService(serviceName = "EmployeeService", endpointInterface = "de.xiekang.server.endpoint.EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Inject // turn regular Java classes into managed objects and to inject them into any other managed object.
    private EmployeeRepository employeeRepository;

    @WebMethod
    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.getEmployee(id);
    }

    @WebMethod
    @Override
    public Employee updateEmployee(int id, String name) {
        return employeeRepository.updateEmployee(id, name);
    }

    @WebMethod
    @Override
    public boolean deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    @WebMethod
    @Override
    public Employee addEmployee(int id, String name) {
        return employeeRepository.addEmployee(id, name);
    }

    @WebMethod
    @Override
    public int countEmployees() {
        return employeeRepository.count();
    }

    @WebMethod
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}
