package listeners;

import configs.JpaUtil;
import models.Employee;
import models.EmployeeHistory;

import javax.persistence.*;
import java.time.LocalDate;

public class EmployeeListener {

    @PreUpdate
    public void preUpdate(Employee employee) {
        logChanges(employee);
    }

    private void logChanges(Employee employee) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Fetch the existing employee from the database
            Employee existingEmployee = entityManager.find(Employee.class, employee.getId());
            if (existingEmployee != null) {
                // Check for changes and log them
                if (existingEmployee.getChildCount() != employee.getChildCount()) {
                    createHistory(entityManager, employee, "childCount", existingEmployee.getChildCount(), employee.getChildCount());
                }
                if (existingEmployee.getLeaveDays() != employee.getLeaveDays()) {
                    createHistory(entityManager, employee, "leaveDays", existingEmployee.getLeaveDays(), employee.getLeaveDays());
                }
                if (existingEmployee.getSalary() != employee.getSalary()) {
                    createHistory(entityManager, employee, "salary", existingEmployee.getSalary(), employee.getSalary());
                }
                if (!existingEmployee.getPosition().equals(employee.getPosition())) {
                    createHistory(entityManager, employee, "position", existingEmployee.getPosition(), employee.getPosition());
                }
                if (!existingEmployee.getPhone_number().equals(employee.getPhone_number())) {
                    createHistory(entityManager, employee, "phoneNumber", existingEmployee.getPhone_number(), employee.getPhone_number());
                }
                if (!existingEmployee.getDepartment().equals(employee.getDepartment())) {
                    createHistory(entityManager, employee, "department", existingEmployee.getDepartment(), employee.getDepartment());
                }
                if (!existingEmployee.getEmail().equals(employee.getEmail())) {
                    createHistory(entityManager, employee, "email", existingEmployee.getEmail(), employee.getEmail());
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void createHistory(EntityManager entityManager, Employee employee, String fieldChanged, Object oldValue, Object newValue) {
        EmployeeHistory history = new EmployeeHistory();
        history.setEmployee(employee);
        history.setFieldChanged(fieldChanged);
        history.setOldValue(oldValue.toString());
        history.setNewValue(newValue.toString());
        history.setChangedBy("Admin");
        history.setChangeDate(LocalDate.now());

        entityManager.persist(history);

    }
}
