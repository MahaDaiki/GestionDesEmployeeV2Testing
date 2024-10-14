package services.serviceInterfaces;

import models.JobOffer;

import java.util.List;

public interface JobOfferServiceInt {
    void createJobOffer(JobOffer jobOffer);
    JobOffer getJobOfferById(Long id);
    List<JobOffer> getAllJobOffers();
    void updateJobOffer(JobOffer jobOffer);
    void deleteJobOffer(Long id);
}
