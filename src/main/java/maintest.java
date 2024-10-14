
import DAO.DaoImplementation.EmployeeDAOImpl;
import DAO.DaoIntferfaces.EmployeeDAOInterface;
import configs.JpaUtil;
import models.Employee;
import services.serviceImplementations.EmployeeServiceImpl;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class maintest {
    public static void main(String[] args) {
//        EntityManager em = JpaUtil.getEntityManager();
//        try {
//            if (em.isOpen()) {
//                System.out.println("Connection to the database established successfully!");
//            } else {
//                System.out.println("Failed to establish connection to the database.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//            JpaUtil.close();
//        }
//        EntityManager entityManager = JpaUtil.getEntityManager();
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(entityManager);
//        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeDAO);
//
//        // Create a new Employee
//        Employee newEmployee = new Employee(
//                "Yassmine",
//                "yassminee@example.com",
//                "0612345678",
//                "password123",
//                LocalDate.of(1990, 4, 15),
//                "SSN12345678",
//                LocalDate.now(),
//                "Software Engineer",
//                "IT Department",
//                20,
//                35000.00,
//                2
//        );
//
//        // Test the createEmployee method
//        try {
//            employeeService.createEmployee(newEmployee);
//            System.out.println("Employee created successfully with ID: " + newEmployee.getId());
//        } catch (Exception e) {
//            System.out.println("Error occurred while creating the employee: " + e.getMessage());
//        }

//        // Modify the existing Employee
//        newEmployee.setPosition("Senior Software Engineer");
//        newEmployee.setSalary(40000.00); // Updating salary
//        newEmployee.setPhone_number("0612345679"); // Updating phone number
//
//        // Test the updateEmployee method
//        try {
//            employeeService.updateEmployee(newEmployee);
//            System.out.println("Employee updated successfully with ID: " + newEmployee.getId());
//        } catch (Exception e) {
//            System.out.println("Error occurred while updating the employee: " + e.getMessage());
//        }
//
//        // Close the EntityManager
//        entityManager.close();
//        JpaUtil.close();
//    }


    }
    }


