package DAO.DaoIntferfaces;

import models.Employee;

import java.util.List;

public interface EmployeeDAOInterface {
    void createEmployee(Employee employee);
    Employee findEmployeeById(Long id);
    List<Employee> findAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
