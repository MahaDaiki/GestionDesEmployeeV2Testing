package services.serviceInterfaces;

import models.Admin;
import models.Candidate;
import models.Employee;
import models.Recruiter;

import javax.servlet.http.HttpSession;

public interface AuthenticationServiceInt {

    void registerCandidate(Candidate candidate);
    Candidate loginAsCandidate(String email, String password);
    Admin loginAsAdmin(String email, String password);
    Employee loginAsEmployee(String email, String password);
    Recruiter loginAsRecruiter(String email, String password);
    void logout(HttpSession session);

}
