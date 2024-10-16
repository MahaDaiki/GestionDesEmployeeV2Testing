package services.serviceInterfaces;

import models.Candidate;

import java.util.List;

public interface CandidateServiceInt {
    void updateCandidate(Candidate candidate);
    Candidate findCandidateById(Long id);
    List<Candidate> findAllCandidates();
    List<Candidate> findCandidateByEmail(String email);
}
