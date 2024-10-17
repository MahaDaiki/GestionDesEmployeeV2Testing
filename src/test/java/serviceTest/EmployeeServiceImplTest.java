    package serviceTest;
    import DAO.DaoImplementation.EmployeeDAOImpl;
    import models.Employee;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.ArgumentCaptor;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import services.serviceImplementations.EmployeeServiceImpl;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.*;
    public class EmployeeServiceImplTest {

    @Mock
        private EmployeeDAOImpl employeeDAO;
    @InjectMocks
        private EmployeeServiceImpl employeeServiceImpl;
         private Employee employee;
    @BeforeEach
            public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee(   "John Doe", "test@test.com", "1234567890", "password123",
                LocalDate.of(1995, 5, 20), "N12345", LocalDate.of(2023, 1, 15),
                "Manager", "HR", 10, 5000.00, 2);
    }

    @Test
        public void testAddEmployee() {
        doNothing().when(employeeDAO).createEmployee(employee);
        employeeServiceImpl.createEmployee(employee);
        verify(employeeDAO).createEmployee(employee);
    }
    @Test
    public void testFindEmployeeById() {

        when(employeeDAO.findEmployeeById(1L)).thenReturn(employee);

        Employee foundEmployee = employeeServiceImpl.findEmployeeById(1L);

        assertEquals(employee, foundEmployee);
        verify(employeeDAO).findEmployeeById(1L);
    }
    @Test
    public void testFindEmployeeById_NotFound() {
        when(employeeDAO.findEmployeeById(1L)).thenReturn(null);
        Employee foundEmployee = employeeServiceImpl.findEmployeeById(1L);

        assertNull(foundEmployee);
        verify(employeeDAO).findEmployeeById(1L);
    }
        @Test
        public void testFindAllEmployees() {
           List<Employee> employees = Arrays.asList(employee, new Employee(
                    "Jad", "jad@email.com", "0987654321", "password321",
                    LocalDate.of(1999, 7, 15), "S4321", LocalDate.of(2021, 3, 20),
                    "Developer", "IT", 15, 6000.00, 1
            ));
            when(employeeDAO.findAllEmployees()).thenReturn(employees);
            List<Employee> foundEmployees = employeeServiceImpl.findAllEmployees();
            assertEquals(2, foundEmployees.size());
            verify(employeeDAO).findAllEmployees();
        }
        @Test
        public void testUpdateEmployee() {
            employee.setPosition("Senior Manager");
            doNothing().when(employeeDAO).updateEmployee(employee);
            employeeServiceImpl.updateEmployee(employee);
            verify(employeeDAO).updateEmployee(employee);
        }

        @Test
        public void testDeleteEmployee() {
            doNothing().when(employeeDAO).deleteEmployee(1L);
            employeeServiceImpl.deleteEmployee(1L);

            verify(employeeDAO).deleteEmployee(1L);
        }

        @Test
        public void testDeleteEmployee_NotFound() {
            doNothing().when(employeeDAO).deleteEmployee(1L);
            employeeServiceImpl.deleteEmployee(9L);
            verify(employeeDAO, times(1)).deleteEmployee(9L);
        }

        @Test
        public void testUpdateEmployee_BirthdateAfterHiringDate_ShouldThrowException() {
            employee.setBirthdate(LocalDate.of(2024, 1, 15));
            employee.setHiringDate(LocalDate.of(2023, 1, 15));

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                employeeServiceImpl.updateEmployee(employee);
            });

            assertEquals("Birthdate cannot be after the hiring date.", exception.getMessage());
        }
        @Test
        public void testAddEmployee_BirthdateAfterHiringDate_ShouldThrowException() {
            Employee invalidEmployee = new Employee("mira", "mira@test.com", "0987654321", "password321",
                    LocalDate.of(2024, 1, 15), "N54321", LocalDate.of(2023, 1, 15),
                    "Senior Manager", "HR", 5, 7000.00, 1);

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                employeeServiceImpl.createEmployee(invalidEmployee);
            });

            assertEquals("Birthdate cannot be after the hiring date.", exception.getMessage());
        }
    }
