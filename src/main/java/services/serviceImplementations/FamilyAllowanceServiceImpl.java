package services.serviceImplementations;

import DAO.DaoImplementation.EmployeeDAOImpl;
import DAO.DaoImplementation.FamilyAllowanceDAOImpl;
import models.Employee;
import models.FamilyAllowance;
import services.serviceInterfaces.FamilyAllowaceServiceInt;

public class FamilyAllowanceServiceImpl implements FamilyAllowaceServiceInt {

private FamilyAllowanceDAOImpl familyAllowanceDao;
public FamilyAllowanceServiceImpl(FamilyAllowanceDAOImpl familyAllowanceDAO) {
    this.familyAllowanceDao = familyAllowanceDAO;
}
    @Override
    public void createFamilyAllowance(FamilyAllowance familyAllowance) {
        familyAllowanceDao.createFamilyAllowance(familyAllowance);
    }
    public double calculateFamilyAllowance(Employee employee) {
        double allowance = 0.0;
        int childCount = employee.getChildCount();
        double salary = employee.getSalary();

        if (salary < 6000) {
            allowance += Math.min(childCount, 3) * 300;
            if (childCount > 3) {
                allowance += Math.min(childCount - 3, 3) * 150;
            }
        } else if (salary > 8000) {
            allowance += Math.min(childCount, 3) * 200;
            if (childCount > 3) {
                allowance += Math.min(childCount - 3, 3) * 110;
            }
        }

        return allowance;
    }

}
