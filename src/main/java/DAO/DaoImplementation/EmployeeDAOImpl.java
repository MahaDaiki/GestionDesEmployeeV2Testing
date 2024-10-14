package DAO.DaoImplementation;

import DAO.DaoIntferfaces.EmployeeDAOInterface;
import configs.JpaUtil;
import models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAOInterface {




    @Override
    public void createEmployee(Employee employee) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }



    @Override
    public Employee findEmployeeById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, id);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Employee> findAllEmployees() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            transaction.commit();
            return employees;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.merge(employee);
            transaction.commit();
        }
        catch(Exception e){
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                entityManager.remove(employee);
            } else {
                System.out.println("Employee with ID " + id + " does not exist.");
            }

            transaction.commit();
        }
        catch(Exception e){
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }


    }
}
