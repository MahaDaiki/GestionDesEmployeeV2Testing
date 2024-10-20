package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidate")

public class Candidate extends Users {

    @Column(nullable = false)
    private String skills;
    @Column(unique = true, nullable = false)
    private String socialSecurityNum;


    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CandidateJobOffer> jobApplications;


    public Candidate() {
        super();
    }

    public Candidate( String name, String email, String phone_number, String password, LocalDate birthdate, String skills, String socialSecurityNum) {
        super(name, email, phone_number, password, birthdate);
        this.skills = skills;
        this.socialSecurityNum = socialSecurityNum;
    }


    @Override
    public LocalDate getBirthdate() {
        return super.getBirthdate();
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public String getSocialSecurityNum() {
        return socialSecurityNum;
    }
    public void setSocialSecurityNum(String socialSecurityNum) {
        this.socialSecurityNum = socialSecurityNum;
    }
    public List<CandidateJobOffer> getJobApplications() {
        return jobApplications;
    }
    public void setJobApplications(List<CandidateJobOffer> jobApplications) {
        this.jobApplications = jobApplications;
    }
}

