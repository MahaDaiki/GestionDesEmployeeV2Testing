package services.serviceImplementations;

import DAO.DaoImplementation.EmployeeDAOImpl;
import DAO.DaoIntferfaces.CandidateJobOfferDAOInt;
import models.CandidateJobOffer;
import services.serviceInterfaces.CandidateJobOfferServiceInt;

import java.util.Collections;
import java.util.List;

public class CandidateJobOfferServiceImpl implements CandidateJobOfferServiceInt {
    private CandidateJobOfferDAOInt candidateJobOfferDAO    ;

    public CandidateJobOfferServiceImpl(CandidateJobOfferDAOInt candidateJobOfferDAO) {
        this.candidateJobOfferDAO = candidateJobOfferDAO;
    }
    @Override
    public void applyForJob(CandidateJobOffer candidateJobOffer) {
        candidateJobOfferDAO.applyForJob(candidateJobOffer);
    }

    @Override
    public void updateApplicationStatus(CandidateJobOffer candidateJobOffer) {
        candidateJobOfferDAO.updateApplicationStatus(candidateJobOffer);
    }

    @Override
    public List<CandidateJobOffer> findByCandidateId(Long candidateId) {
        return candidateJobOfferDAO.findByCandidateId(candidateId);
    }

    @Override
    public List<CandidateJobOffer> findByJobOfferId(Long jobOfferId) {
        return candidateJobOfferDAO.findByJobOfferId(jobOfferId);
    }

    @Override
    public CandidateJobOffer findApplication(Long candidateId, Long jobOfferId) {
        return candidateJobOfferDAO.findApplication(candidateId, jobOfferId);
    }
}
