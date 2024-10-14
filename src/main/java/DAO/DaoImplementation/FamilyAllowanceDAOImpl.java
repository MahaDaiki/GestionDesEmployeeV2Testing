package DAO.DaoImplementation;

import DAO.DaoIntferfaces.FamilyAllowanceDAOInt;
import configs.JpaUtil;
import models.FamilyAllowance;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class FamilyAllowanceDAOImpl implements FamilyAllowanceDAOInt {

    public void createFamilyAllowance(FamilyAllowance familyAllowance) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(familyAllowance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
          e.printStackTrace();
        }
    }
}
