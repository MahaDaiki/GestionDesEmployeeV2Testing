package models;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employeehistory")
public class EmployeeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    private String fieldChanged;
    private String oldValue;
    private String newValue;
    private String changedBy;
    private LocalDate changeDate;


    public EmployeeHistory() {}

    public EmployeeHistory(Employee employee, String fieldChanged, String oldValue, String newValue, String changedBy, LocalDate changeDate) {
        this.employee = employee;
        this.fieldChanged = fieldChanged;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changedBy = changedBy;
        this.changeDate = changeDate;
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
    public String getFieldChanged() {
        return fieldChanged;
    }
    public void setFieldChanged(String fieldChanged) {
        this.fieldChanged = fieldChanged;
    }
    public String getOldValue() {
        return oldValue;
    }
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    public String getChangedBy() {
        return changedBy;
    }
    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }
    public LocalDate getChangeDate() {
        return changeDate;
    }
    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

}
