package models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "recruiter")
public class Recruiter extends Users {

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<JobOffer> jobOffers;
    public Recruiter() {
        super();
    }


    public Recruiter(String name, String email, String phone_number, String password, LocalDate birthdate) {
        super(name, email, phone_number, password, birthdate);
    }

}
