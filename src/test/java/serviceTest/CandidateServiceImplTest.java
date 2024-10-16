package serviceTest;


import DAO.DaoIntferfaces.CandidateDAOInt;
import services.serviceImplementations.CandidateServiceImpl;
import models.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class CandidateServiceImplTest {

    @Mock
    private CandidateDAOInt candidateDAO;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateCandidate() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("John Doe");
        candidateService.updateCandidate(candidate);
        verify(candidateDAO, times(1)).updateCandidate(candidate);
    }

    @Test
    public void testFindCandidateById() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("Salma");
        when(candidateDAO.findCandidateById(1L)).thenReturn(candidate);

        Candidate result = candidateService.findCandidateById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Salma", result.getName());

        verify(candidateDAO, times(1)).findCandidateById(1L);
    }

    @Test
    public void testFindAllCandidates() {
        Candidate candidate1 = new Candidate();
        candidate1.setId(1L);
        candidate1.setName("Joe");

        Candidate candidate2 = new Candidate();
        candidate2.setId(2L);
        candidate2.setName("Jane");

        when(candidateDAO.findAllCandidates()).thenReturn(Arrays.asList(candidate1, candidate2));

        List<Candidate> result = candidateService.findAllCandidates();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Joe", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());

        verify(candidateDAO, times(1)).findAllCandidates();
    }

    @Test
    public void testFindCandidateByEmail() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("test");
        candidate.setEmail("test@example.com");
        when(candidateDAO.findCandidateByEmail("test@example.com")).thenReturn(Collections.singletonList(candidate));

        List<Candidate> result = candidateService.findCandidateByEmail("test@example.com");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getEmail());

        verify(candidateDAO, times(1)).findCandidateByEmail("test@example.com");
    }

}
