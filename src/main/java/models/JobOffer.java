package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "joboffer")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOfferId;

    private String jobTitle;
    private String description;
    private String requirements;
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateJobOffer> jobApplications;


    public JobOffer() {
    }


    public JobOffer(String jobTitle, String description, String requirements, LocalDate publishDate, Recruiter recruiter) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.requirements = requirements;
        this.publishDate = publishDate;
        this.recruiter = recruiter;
    }



    public Long getJobOfferId() {
        return jobOfferId;
    }

    public void setJobOfferId(Long jobOfferId) {
        this.jobOfferId = jobOfferId;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
     public String getJobTitle() {
        return jobTitle;
     }
     public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
     }
     public String getDescription() {
        return description;
     }
     public void setDescription(String description) {
        this.description = description;
     }
     public String getRequirements() {
        return requirements;
     }
     public void setRequirements(String requirements) {
        this.requirements = requirements;
     }
     public LocalDate getPublishDate() {
        return publishDate;
     }
     public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
     }
    public List<CandidateJobOffer> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<CandidateJobOffer> jobApplications) {
        this.jobApplications = jobApplications;
    }
}