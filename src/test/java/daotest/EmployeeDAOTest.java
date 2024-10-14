package daotest;

import models.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeDAOTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test-persistence-unit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateEmployee() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Employee employee = new Employee(
                "test",
                "test@example.com",
                "0612345678",
                "password123",
                LocalDate.of(1990, 4, 15),
                "SSN12345678",
                LocalDate.now(),
                "Software Engineer",
                "IT Department",
                20,
                35000.00,
                2
        );

        entityManager.persist(employee);
        transaction.commit();

        assertNotNull(employee.getId());
        assertEquals("test", employee.getName());
    }

    @Test
    public void testFindEmployeeById() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Employee employee = new Employee(
                "Ahmed",
                "ahmed@example.com",
                "0612345678",
                "password123",
                LocalDate.of(1990, 4, 15),
                "SSN87654321",
                LocalDate.now(),
                "Engineer",
                "HR Department",
                15,
                25000.00,
                1
        );
        entityManager.persist(employee);
        transaction.commit();


        Employee foundEmployee = entityManager.find(Employee.class, employee.getId());


        assertNotNull(foundEmployee);
        assertEquals(employee.getId(), foundEmployee.getId());
        assertEquals("Ahmed", foundEmployee.getName());
    }
}
