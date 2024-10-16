package DAO.DaoImplementation;

import DAO.DaoIntferfaces.CandidateJobOfferDAOInt;
import configs.JpaUtil;
import models.CandidateJobOffer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

public class CandidateJobOfferDAOImpl implements CandidateJobOfferDAOInt {

    @Override
    public void applyForJob(CandidateJobOffer candidateJobOffer) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(candidateJobOffer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

        }

    }

    @Override
    public void updateApplicationStatus(CandidateJobOffer candidateJobOffer) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(candidateJobOffer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<CandidateJobOffer> findByCandidateId(Long candidateId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT cjo FROM CandidateJobOffer cjo WHERE cjo.candidate.id = :candidateId", CandidateJobOffer.class)
                    .setParameter("candidateId", candidateId)
                    .getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<CandidateJobOffer> findByJobOfferId(Long jobOfferId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT cjo FROM CandidateJobOffer cjo WHERE cjo.jobOffer.id = :jobOfferId", CandidateJobOffer.class)
                    .setParameter("jobOfferId", jobOfferId)
                    .getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public CandidateJobOffer findApplication(Long candidateId, Long jobOfferId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT cjo FROM CandidateJobOffer cjo WHERE cjo.candidate.id = :candidateId AND cjo.jobOffer.id = :jobOfferId", CandidateJobOffer.class)
                    .setParameter("candidateId", candidateId)
                    .setParameter("jobOfferId", jobOfferId)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}