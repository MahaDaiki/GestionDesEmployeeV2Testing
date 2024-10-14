package DAO.DaoIntferfaces;

import models.JobOffer;

import java.util.List;

public interface JobOfferDaoInt {
    void createJobOffer(JobOffer jobOffer);
    JobOffer getJobOfferById(Long id);
    List<JobOffer> getAllJobOffers();
    void updateJobOffer(JobOffer jobOffer);
    void deleteJobOffer(Long id);
}
