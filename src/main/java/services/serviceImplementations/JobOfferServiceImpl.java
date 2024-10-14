package services.serviceImplementations;

import DAO.DaoImplementation.JobOfferDAOImpl;
import models.JobOffer;
import services.serviceInterfaces.JobOfferServiceInt;

import java.util.List;

public class JobOfferServiceImpl implements JobOfferServiceInt {
    private JobOfferDAOImpl jobOfferDAO;

    public JobOfferServiceImpl() {
        this.jobOfferDAO = new JobOfferDAOImpl();
    }

@Override
    public void createJobOffer(JobOffer jobOffer) {
        jobOfferDAO.createJobOffer(jobOffer);
    }
@Override
    public List<JobOffer> getAllJobOffers() {
        return jobOfferDAO.getAllJobOffers();
    }

@Override
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferDAO.updateJobOffer(jobOffer);
    }

@Override
    public void deleteJobOffer(Long jobOfferId) {
        jobOfferDAO.deleteJobOffer(jobOfferId);
    }
@Override
    public JobOffer getJobOfferById(Long jobOfferId) {
        return jobOfferDAO.getJobOfferById(jobOfferId);
    }
}
