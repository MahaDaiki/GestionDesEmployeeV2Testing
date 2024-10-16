package DAO.DaoImplementation;

import DAO.DaoIntferfaces.CandidateDAOInt;
import DAO.DaoIntferfaces.CandidateJobOfferDAOInt;
import configs.JpaUtil;
import models.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

public class CandidateDAOImpl implements CandidateDAOInt {
    @Override
    public void updateCandidate(Candidate candidate) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(candidate);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Candidate findCandidateById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.find(Candidate.class, id);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Candidate> findAllCandidates() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Candidate c", Candidate.class).getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Candidate> findCandidateByEmail(String email) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Candidate c WHERE c.email = :email", Candidate.class)
                    .setParameter("email", email)
                    .getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

}
