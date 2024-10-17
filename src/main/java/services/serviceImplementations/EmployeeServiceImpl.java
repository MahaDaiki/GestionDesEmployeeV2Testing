package services.serviceImplementations;

import DAO.DaoImplementation.EmployeeDAOImpl;
import models.Employee;
import services.serviceInterfaces.EmployeeServiceInt;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeServiceInt {
    private EmployeeDAOImpl employeeDao;

    public EmployeeServiceImpl(EmployeeDAOImpl employeeDAO) {
        this.employeeDao = employeeDAO;
    }

    @Override
    public void createEmployee(Employee employee) {
        validateEmployeeDates(employee);
        employeeDao.createEmployee(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public void updateEmployee(Employee employee) {
        validateEmployeeDates(employee);
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }

    private void validateEmployeeDates(Employee employee) {
        if (employee.getBirthdate() != null && employee.getHiringDate() != null) {
            if (employee.getBirthdate().isAfter(employee.getHiringDate())) {
                throw new IllegalArgumentException("Birthdate cannot be after the hiring date.");
            }
        }
    }
}
