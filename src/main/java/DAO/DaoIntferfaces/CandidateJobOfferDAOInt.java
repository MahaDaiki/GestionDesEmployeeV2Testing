package DAO.DaoIntferfaces;

import models.CandidateJobOffer;

import java.util.List;

public interface CandidateJobOfferDAOInt {
    void applyForJob(CandidateJobOffer candidateJobOffer);
    void updateApplicationStatus(CandidateJobOffer candidateJobOffer);
    List<CandidateJobOffer> findByCandidateId(Long candidateId);
    List<CandidateJobOffer> findByJobOfferId(Long jobOfferId);
    CandidateJobOffer findApplication(Long candidateId, Long jobOfferId);
}
