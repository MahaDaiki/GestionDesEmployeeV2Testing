package DAO.DaoIntferfaces;

import models.Candidate;

import java.util.List;

public interface CandidateDAOInt {
    void updateCandidate(Candidate candidate);
    Candidate findCandidateById(Long id);
    List<Candidate> findAllCandidates();
    List<Candidate> findCandidateByEmail(String email);
}
