package DAO.DaoImplementation;

import DAO.DaoIntferfaces.JobOfferDaoInt;
import configs.JpaUtil;
import models.JobOffer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

public class JobOfferDAOImpl implements JobOfferDaoInt {
    @Override
    public void createJobOffer(JobOffer jobOffer) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(jobOffer);
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
    public JobOffer getJobOfferById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.find(JobOffer.class, id);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<JobOffer> getAllJobOffers() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT j FROM JobOffer j", JobOffer.class).getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(jobOffer);
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
    public void deleteJobOffer(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            JobOffer jobOffer = entityManager.find(JobOffer.class, id);
            if (jobOffer != null) {
                entityManager.remove(jobOffer);
            }
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
}
