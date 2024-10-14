package DAO.DaoIntferfaces;

import models.Admin;
import models.Candidate;
import models.Employee;
import models.Recruiter;

import javax.servlet.http.HttpSession;

public interface AutheticationDAOInt {
    void registerCandidate(Candidate candidate);
    Candidate loginAsCandidate(String email, String password);
    Admin loginAsAdmin(String email, String password);
    Employee loginAsEmployee(String email, String password);
    Recruiter loginAsRecruiter(String email, String password);


}
