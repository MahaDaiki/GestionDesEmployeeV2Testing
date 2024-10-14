package models;

import enums.ApplicationStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "candidate_joboffer")
public class CandidateJobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private LocalDate appDate;


    public CandidateJobOffer() {
    }

    public CandidateJobOffer(Candidate candidate, JobOffer jobOffer, ApplicationStatus applicationStatus, LocalDate appDate) {
        this.candidate = candidate;
        this.jobOffer = jobOffer;
        this.applicationStatus = applicationStatus;
        this.appDate = appDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
    }

}