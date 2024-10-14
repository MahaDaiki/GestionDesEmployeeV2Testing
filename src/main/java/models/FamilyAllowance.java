package models;

import javax.persistence.*;

@Entity
@Table(name = "famillyallowance")
public class FamilyAllowance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private double totalSalary;




    public FamilyAllowance(Employee employee, double totalSalary) {
        this.employee = employee;
        this.totalSalary = totalSalary;
    }

    public FamilyAllowance() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public double getTotalSalary() {
        return totalSalary;
    }
    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        return "FamilyAllowance{" +
                "id=" + id +
                ", employee=" + (employee != null ? employee.getName() : "null") +  // Assuming Employee has a getName() method
                ", totalSalary=" + totalSalary +
                '}';
    }
}


