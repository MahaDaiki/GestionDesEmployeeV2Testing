package DAO.DaoImplementation;

import DAO.DaoIntferfaces.AutheticationDAOInt;
import models.Admin;
import models.Candidate;
import models.Employee;
import models.Recruiter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

public class AuthenticationDAOImpl implements AutheticationDAOInt {

    private EntityManager entityManager;

    public AuthenticationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void registerCandidate(Candidate candidate) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(candidate);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Candidate loginAsCandidate(String email, String password) {
        try {
            TypedQuery<Candidate> query = entityManager.createQuery(
                    "SELECT c FROM Candidate c WHERE c.email = :email AND c.password = :password", Candidate.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Admin loginAsAdmin(String email, String password) {
        try {
            TypedQuery<Admin> query = entityManager.createQuery(
                    "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password", Admin.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Employee loginAsEmployee(String email, String password) {
        try {
            TypedQuery<Employee> query = entityManager.createQuery(
                    "SELECT e FROM Employee e WHERE e.email = :email AND e.password = :password", Employee.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Recruiter loginAsRecruiter(String email, String password) {
        try {
            TypedQuery<Recruiter> query = entityManager.createQuery(
                    "SELECT r FROM Recruiter r WHERE r.email = :email AND r.password = :password", Recruiter.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }



}
