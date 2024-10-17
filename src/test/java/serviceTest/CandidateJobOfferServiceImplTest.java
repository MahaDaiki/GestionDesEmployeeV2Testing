package serviceTest;

import DAO.DaoIntferfaces.CandidateJobOfferDAOInt;
import models.CandidateJobOffer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.serviceImplementations.CandidateJobOfferServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CandidateJobOfferServiceImplTest {

    @Mock
    private CandidateJobOfferDAOInt candidateJobOfferDAO;

    @InjectMocks
    private CandidateJobOfferServiceImpl candidateJobOfferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyForJob() {
        CandidateJobOffer candidateJobOffer = new CandidateJobOffer();
        candidateJobOffer.setId(1L);

        candidateJobOfferService.applyForJob(candidateJobOffer);

        verify(candidateJobOfferDAO, times(1)).applyForJob(candidateJobOffer);
    }

    @Test
    public void testUpdateApplicationStatus() {
        CandidateJobOffer candidateJobOffer = new CandidateJobOffer();
        candidateJobOffer.setId(1L);
        candidateJobOfferService.updateApplicationStatus(candidateJobOffer);
        verify(candidateJobOfferDAO, times(1)).updateApplicationStatus(candidateJobOffer);
    }

    @Test
    public void testFindByCandidateId() {
        Long candidateId = 1L;
        CandidateJobOffer candidateJobOffer1 = new CandidateJobOffer();
        candidateJobOffer1.setId(1L);
        CandidateJobOffer candidateJobOffer2 = new CandidateJobOffer();
        candidateJobOffer2.setId(2L);

        when(candidateJobOfferDAO.findByCandidateId(candidateId)).thenReturn(Arrays.asList(candidateJobOffer1, candidateJobOffer2));

        List<CandidateJobOffer> result = candidateJobOfferService.findByCandidateId(candidateId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(candidateJobOfferDAO, times(1)).findByCandidateId(candidateId);
    }

    @Test
    public void testFindByJobOfferId() {
        Long jobOfferId = 1L;
        CandidateJobOffer candidateJobOffer1 = new CandidateJobOffer();
        candidateJobOffer1.setId(1L);
        CandidateJobOffer candidateJobOffer2 = new CandidateJobOffer();
        candidateJobOffer2.setId(2L);

        when(candidateJobOfferDAO.findByJobOfferId(jobOfferId)).thenReturn(Arrays.asList(candidateJobOffer1, candidateJobOffer2));

        List<CandidateJobOffer> result = candidateJobOfferService.findByJobOfferId(jobOfferId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(candidateJobOfferDAO, times(1)).findByJobOfferId(jobOfferId);
    }

    @Test
    public void testFindApplication() {
        Long candidateId = 1L;
        Long jobOfferId = 1L;
        CandidateJobOffer candidateJobOffer = new CandidateJobOffer();
        candidateJobOffer.setId(1L);

        when(candidateJobOfferDAO.findApplication(candidateId, jobOfferId)).thenReturn(candidateJobOffer);

        CandidateJobOffer result = candidateJobOfferService.findApplication(candidateId, jobOfferId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(candidateJobOfferDAO, times(1)).findApplication(candidateId, jobOfferId);
    }
}