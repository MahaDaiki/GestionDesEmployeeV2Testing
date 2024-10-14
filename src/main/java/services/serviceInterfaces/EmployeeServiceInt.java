package services.serviceInterfaces;

import models.Employee;

import java.util.List;

public interface EmployeeServiceInt {
    void createEmployee(Employee employee);
    Employee findEmployeeById(Long id);
    List<Employee> findAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
