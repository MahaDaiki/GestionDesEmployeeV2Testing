package services.serviceImplementations;

import DAO.DaoIntferfaces.CandidateDAOInt;
import models.Candidate;
import services.serviceInterfaces.CandidateServiceInt;

import java.util.Collections;
import java.util.List;

public class CandidateServiceImpl implements CandidateServiceInt {
    private CandidateDAOInt CandidateDao;

    public CandidateServiceImpl(CandidateDAOInt CandidateDao) {
        this.CandidateDao = CandidateDao;
    }
    @Override
    public void updateCandidate(Candidate candidate) {
        CandidateDao.updateCandidate(candidate);
    }

    @Override
    public Candidate findCandidateById(Long id) {
        return CandidateDao.findCandidateById(id);
    }

    @Override
    public List<Candidate> findAllCandidates() {
        return CandidateDao.findAllCandidates();
    }

    @Override
    public List<Candidate> findCandidateByEmail(String email) {
        return CandidateDao.findCandidateByEmail(email);
    }
}
