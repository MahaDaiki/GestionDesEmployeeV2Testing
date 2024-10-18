package serviceTest;

import models.Admin;
import models.Candidate;
import models.Employee;
import models.Recruiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.serviceImplementations.AuthenticationServiceImpl;
import DAO.DaoImplementation.AuthenticationDAOImpl;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {

    @Mock
    private AuthenticationDAOImpl authenticationDao;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterCandidate() {
        Candidate candidate = new Candidate();
        candidate.setEmail("test@example.com");

        authenticationService.registerCandidate(candidate);

        verify(authenticationDao, times(1)).registerCandidate(candidate);
    }

    @Test
    public void testLoginAsCandidateSuccess() {
        Candidate candidate = new Candidate();
        candidate.setEmail("candidate@example.com");
        candidate.setPassword("password");

        when(authenticationDao.loginAsCandidate("candidate@example.com", "password"))
                .thenReturn(candidate);

        Candidate result = authenticationService.loginAsCandidate("candidate@example.com", "password");

        assertNotNull(result);
        assertEquals("candidate@example.com", result.getEmail());
        verify(authenticationDao, times(1)).loginAsCandidate("candidate@example.com", "password");
    }

    @Test
    public void testLoginAsCandidateFailure() {
        when(authenticationDao.loginAsCandidate("wrong@example.com", "wrongpassword"))
                .thenReturn(null);

        Candidate result = authenticationService.loginAsCandidate("wrong@example.com", "wrongpassword");

        assertNull(result);
        verify(authenticationDao, times(1)).loginAsCandidate("wrong@example.com", "wrongpassword");
    }

    @Test
    public void testLoginAsAdminSuccess() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");

        when(authenticationDao.loginAsAdmin("admin@example.com", "password")).thenReturn(admin);

        Admin result = authenticationService.loginAsAdmin("admin@example.com", "password");

        assertNotNull(result);
        assertEquals("admin@example.com", result.getEmail());
        verify(authenticationDao, times(1)).loginAsAdmin("admin@example.com", "password");
    }

    @Test
    public void testLoginAsAdminFailure() {
        when(authenticationDao.loginAsAdmin("wrong@example.com", "wrongpassword")).thenReturn(null);

        Admin result = authenticationService.loginAsAdmin("wrong@example.com", "wrongpassword");

        assertNull(result);
        verify(authenticationDao, times(1)).loginAsAdmin("wrong@example.com", "wrongpassword");
    }

    @Test
    public void testLoginAsEmployeeSuccess() {
        Employee employee = new Employee();
        employee.setEmail("employee@example.com");

        when(authenticationDao.loginAsEmployee("employee@example.com", "password")).thenReturn(employee);

        Employee result = authenticationService.loginAsEmployee("employee@example.com", "password");

        assertNotNull(result);
        assertEquals("employee@example.com", result.getEmail());
        verify(authenticationDao, times(1)).loginAsEmployee("employee@example.com", "password");
    }

    @Test
    public void testLoginAsEmployeeFailure() {
        when(authenticationDao.loginAsEmployee("wrong@example.com", "wrongpassword")).thenReturn(null);

        Employee result = authenticationService.loginAsEmployee("wrong@example.com", "wrongpassword");

        assertNull(result);
        verify(authenticationDao, times(1)).loginAsEmployee("wrong@example.com", "wrongpassword");
    }

    @Test
    public void testLoginAsRecruiterSuccess() {
        Recruiter recruiter = new Recruiter();
        recruiter.setEmail("recruiter@example.com");

        when(authenticationDao.loginAsRecruiter("recruiter@example.com", "password")).thenReturn(recruiter);

        Recruiter result = authenticationService.loginAsRecruiter("recruiter@example.com", "password");

        assertNotNull(result);
        assertEquals("recruiter@example.com", result.getEmail());
        verify(authenticationDao, times(1)).loginAsRecruiter("recruiter@example.com", "password");
    }

    @Test
    public void testLoginAsRecruiterFailure() {
        when(authenticationDao.loginAsRecruiter("wrong@example.com", "wrongpassword")).thenReturn(null);

        Recruiter result = authenticationService.loginAsRecruiter("wrong@example.com", "wrongpassword");

        assertNull(result);
        verify(authenticationDao, times(1)).loginAsRecruiter("wrong@example.com", "wrongpassword");
    }

    @Test
    public void testLogout() {
        authenticationService.logout(session);
        verify(session, times(1)).invalidate();
    }
    @Test
    public void testRegisterCandidate_DuplicateEmail() {
        Candidate candidate = new Candidate();
        candidate.setEmail("duplicate@example.com");

        doThrow(new IllegalArgumentException("Email already exists"))
                .when(authenticationDao).registerCandidate(candidate);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.registerCandidate(candidate);
        });

        assertEquals("Email already exists", exception.getMessage());
    }
    @Test
    public void testLoginWithEmptyCredentials() {
        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.loginAsCandidate("", "");
        });
    }


}
