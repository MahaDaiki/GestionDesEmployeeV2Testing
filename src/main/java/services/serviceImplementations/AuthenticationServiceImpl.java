package services.serviceImplementations;

import DAO.DaoImplementation.AuthenticationDAOImpl;
import models.Admin;
import models.Candidate;
import models.Employee;
import models.Recruiter;
import services.serviceInterfaces.AuthenticationServiceInt;

import javax.servlet.http.HttpSession;

public class AuthenticationServiceImpl implements AuthenticationServiceInt {
    private AuthenticationDAOImpl authenticationDao;

    public AuthenticationServiceImpl(AuthenticationDAOImpl authenticationDao) {
        this.authenticationDao = authenticationDao;
    }


    @Override
    public void registerCandidate(Candidate candidate) {
            authenticationDao.registerCandidate(candidate);
    }

    @Override
    public Candidate loginAsCandidate(String email, String password) {
        return authenticationDao.loginAsCandidate(email, password);
    }

    @Override
    public Admin loginAsAdmin(String email, String password) {
        return authenticationDao.loginAsAdmin(email, password);
    }

    @Override
    public Employee loginAsEmployee(String email, String password) {
        return authenticationDao.loginAsEmployee(email, password);
    }

    @Override
    public Recruiter loginAsRecruiter(String email, String password) {
        return authenticationDao.loginAsRecruiter(email, password);
    }

    @Override
    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }
}
