package serviceTest;

import models.Employee;
import models.FamilyAllowance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import DAO.DaoImplementation.FamilyAllowanceDAOImpl;
import services.serviceImplementations.FamilyAllowanceServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FamilyAllowanceServiceImplTest {

    @Mock
    private FamilyAllowanceDAOImpl famillyAllowancrDAO;

    @InjectMocks
    private FamilyAllowanceServiceImpl familyAllowanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFamilyAllowance() {
        FamilyAllowance familyAllowance = new FamilyAllowance();
        doNothing().when(famillyAllowancrDAO).createFamilyAllowance(familyAllowance);

        familyAllowanceService.createFamilyAllowance(familyAllowance);
        verify(famillyAllowancrDAO).createFamilyAllowance(familyAllowance);
    }

    @Test
    public void testCalculateFamilyAllowance() {
        Employee employee = new Employee();
        employee.setChildCount(4);
        employee.setSalary(5000);

        double allowance = familyAllowanceService.calculateFamilyAllowance(employee);
        assertEquals(1050 , allowance);
    }

    @Test
    public void TestCalculateFamilyAllowanceHighSalary(){
        Employee employee = new Employee();
        employee.setChildCount(5);
        employee.setSalary(9000);
        double allowance = familyAllowanceService.calculateFamilyAllowance(employee);
        assertEquals(830 , allowance);
    }

    @Test
    public void TestCalculateFamilyAllowanceExactSalaryLimit(){
        Employee employee = new Employee();
        employee.setChildCount(3);
        employee.setSalary(6000);
        double allowance = familyAllowanceService.calculateFamilyAllowance(employee);
        assertEquals(0 , allowance);
    }
}
