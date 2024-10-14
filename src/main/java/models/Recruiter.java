package models;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "recruiter")
public class Recruiter extends Users {


    public Recruiter() {
        super();
    }


    public Recruiter(String name, String email, String phone_number, String password, LocalDate birthdate) {
        super(name, email, phone_number, password, birthdate);
    }

}
