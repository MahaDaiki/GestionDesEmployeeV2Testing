package serviceTest;

import models.JobOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.serviceImplementations.JobOfferServiceImpl;
import DAO.DaoImplementation.JobOfferDAOImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobOfferServiceImplTest {

    @Mock
    private JobOfferDAOImpl jobOfferDAO;

    @InjectMocks
    private JobOfferServiceImpl jobOfferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateJobOffer() {
        JobOffer jobOffer = new JobOffer();
        doNothing().when(jobOfferDAO).createJobOffer(jobOffer);

        jobOfferService.createJobOffer(jobOffer);

        verify(jobOfferDAO).createJobOffer(jobOffer);
    }

    @Test
    public void testGetJobOfferById() {

        JobOffer jobOffer = new JobOffer();
        when(jobOfferDAO.getJobOfferById(1L)).thenReturn(jobOffer);

        JobOffer result = jobOfferService.getJobOfferById(1L);

        assertNotNull(result);
        assertEquals(jobOffer, result);
        verify(jobOfferDAO).getJobOfferById(1L);
    }

    @Test
    public void testGetJobOfferById_NotFound() {
        when(jobOfferDAO.getJobOfferById(1L)).thenReturn(null);
        JobOffer result = jobOfferService.getJobOfferById(1L);

        assertNull(result);
        verify(jobOfferDAO).getJobOfferById(1L);
    }

    @Test
    public void testGetAllJobOffers() {
        JobOffer jobOffer1 = new JobOffer();
        JobOffer jobOffer2 = new JobOffer();
        List<JobOffer> jobOffers = Arrays.asList(jobOffer1, jobOffer2);
        when(jobOfferDAO.getAllJobOffers()).thenReturn(jobOffers);
        List<JobOffer> result = jobOfferService.getAllJobOffers();
        assertEquals(2, result.size());
        verify(jobOfferDAO).getAllJobOffers();
    }

    @Test
    public void testUpdateJobOffer() {
        JobOffer jobOffer = new JobOffer();
        doNothing().when(jobOfferDAO).updateJobOffer(jobOffer);
        jobOfferService.updateJobOffer(jobOffer);
        verify(jobOfferDAO, times(1)).updateJobOffer(jobOffer);
    }

    @Test
    public void testDeleteJobOffer() {
        Long jobOfferId = 1L;
        doNothing().when(jobOfferDAO).deleteJobOffer(jobOfferId);
        jobOfferService.deleteJobOffer(jobOfferId);
        verify(jobOfferDAO, times(1)).deleteJobOffer(jobOfferId);
    }

    @Test
    public void testDeleteJobOffer_NonExistingId() {
        Long jobOfferId = 1L;
        doThrow(new IllegalArgumentException("Job offer not found")).when(jobOfferDAO).deleteJobOffer(jobOfferId);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jobOfferService.deleteJobOffer(jobOfferId);
        });
        assertEquals("Job offer not found", exception.getMessage());
        verify(jobOfferDAO).deleteJobOffer(jobOfferId);
    }


}
