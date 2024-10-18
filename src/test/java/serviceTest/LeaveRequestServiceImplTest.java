package serviceTest;
import DAO.DaoImplementation.LeaveRequestDAOImpl;
import enums.LeaveRequestStatus;
import models.Employee;
import models.LeaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.serviceImplementations.LeaveRequestServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LeaveRequestServiceImplTest {
    @Mock
    private LeaveRequestDAOImpl leaveRequestDAO;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestServiceImpl;

    private Employee employee;
    private LeaveRequest leaveRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        leaveRequest = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", null, employee);
    }

    @Test
    public void testCreateLeaveRequest_Valid() {
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList());

        leaveRequestServiceImpl.createLeaveRequest(leaveRequest);

        verify(leaveRequestDAO, times(1)).createLeaveRequest(leaveRequest);
        assertEquals(LeaveRequestStatus.PENDING, leaveRequest.getStatus());
    }

    @Test
    public void testCreateLeaveRequest_OverlappingDates() {
        LeaveRequest existingRequest = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", LeaveRequestStatus.APPROVED, employee);
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList(existingRequest));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestServiceImpl.createLeaveRequest(leaveRequest);
        });

        assertEquals("The leave request dates overlap with existing approved requests.", exception.getMessage());
        verify(leaveRequestDAO, never()).createLeaveRequest(any());
    }


    @Test
    public void testFindLeaveRequestById() {
        when(leaveRequestDAO.findLeaveRequestById(1L)).thenReturn(leaveRequest);

        LeaveRequest result = leaveRequestServiceImpl.findLeaveRequestById(1L);

        assertNotNull(result);
        assertEquals(leaveRequest, result);
    }

    @Test
    public void testFindAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = Arrays.asList(leaveRequest);
        when(leaveRequestDAO.findAllLeaveRequests()).thenReturn(leaveRequests);

        List<LeaveRequest> result = leaveRequestServiceImpl.findAllLeaveRequests();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(leaveRequest, result.get(0));
    }

    @Test
    public void testUpdateLeaveRequestStatus() {
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveRequestStatus.PENDING);

        leaveRequestServiceImpl.updateLeaveRequestStatus(1L, LeaveRequestStatus.APPROVED);

        verify(leaveRequestDAO, times(1)).updateLeaveRequestStatus(1L, LeaveRequestStatus.APPROVED);
        assertEquals(LeaveRequestStatus.PENDING, leaveRequest.getStatus());
    }

    @Test
    public void testFindLeaveRequestsByEmployeeId() {
        LeaveRequest existingRequest = new LeaveRequest(LocalDate.of(2024, 10, 5), LocalDate.of(2024, 10, 10), "Vacation", LeaveRequestStatus.APPROVED, employee);
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList(existingRequest));

        List<LeaveRequest> result = leaveRequestServiceImpl.findLeaveRequestsByEmployeeId(employee.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(existingRequest, result.get(0));
    }
    @Test
    public void testCreateLeaveRequest_OverlappingDates_StartDate() {
        LeaveRequest existingRequest = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", LeaveRequestStatus.APPROVED, employee);
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList(existingRequest));

        LeaveRequest newRequest = new LeaveRequest(LocalDate.of(2024, 10, 19), LocalDate.of(2024, 10, 25), "Vacation", null, employee);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestServiceImpl.createLeaveRequest(newRequest);
        });

        assertEquals("The leave request dates overlap with existing approved requests.", exception.getMessage());
        verify(leaveRequestDAO, never()).createLeaveRequest(any());
    }

    @Test
    public void testCreateLeaveRequest_OverlappingDates_EndDate() {
        LeaveRequest existingRequest = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", LeaveRequestStatus.APPROVED, employee);
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList(existingRequest));

        LeaveRequest newRequest = new LeaveRequest(LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 17), "Vacation", null, employee);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestServiceImpl.createLeaveRequest(newRequest);
        });

        assertEquals("The leave request dates overlap with existing approved requests.", exception.getMessage());
        verify(leaveRequestDAO, never()).createLeaveRequest(any());
    }

    @Test
    public void testCreateLeaveRequest_SameStartEndDate() {
        LeaveRequest existingRequest = new LeaveRequest(LocalDate.of(2024, 10, 20), LocalDate.of(2024, 10, 25), "Vacation", LeaveRequestStatus.APPROVED, employee);
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList(existingRequest));

        LeaveRequest newRequest = new LeaveRequest(LocalDate.of(2024, 10, 20), LocalDate.of(2024, 10, 22), "Vacation", null, employee);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            leaveRequestServiceImpl.createLeaveRequest(newRequest);
        });

        assertEquals("The leave request dates overlap with existing approved requests.", exception.getMessage());
        verify(leaveRequestDAO, never()).createLeaveRequest(any());
    }

    @Test
    public void testAreDatesOverlapping_Overlapping() {
        LeaveRequest request1 = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", LeaveRequestStatus.APPROVED, employee);
        LeaveRequest request2 = new LeaveRequest(LocalDate.of(2024, 10, 18), LocalDate.of(2024, 10, 22), "Sick Leave", LeaveRequestStatus.PENDING, employee);

        boolean result = leaveRequestServiceImpl.areDatesOverlapping(request1, request2);
        assertTrue(result);
    }

    @Test
    public void testAreDatesOverlapping_NonOverlapping() {
        LeaveRequest request1 = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", LeaveRequestStatus.APPROVED, employee);
        LeaveRequest request2 = new LeaveRequest(LocalDate.of(2024, 10, 21), LocalDate.of(2024, 10, 25), "Sick Leave", LeaveRequestStatus.PENDING, employee);

        boolean result = leaveRequestServiceImpl.areDatesOverlapping(request1, request2);
        assertFalse(result);
    }

    @Test
    public void testAreDatesOverlapping_SameStartDateEndDate() {
        LeaveRequest request1 = new LeaveRequest(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), "Vacation", LeaveRequestStatus.APPROVED, employee);
        LeaveRequest request2 = new LeaveRequest(LocalDate.of(2024, 10, 20), LocalDate.of(2024, 10, 25), "Sick Leave", LeaveRequestStatus.PENDING, employee);

        boolean result = leaveRequestServiceImpl.areDatesOverlapping(request1, request2);

        assertTrue(result);
    }
    @Test
    public void testIsLeaveRequestValid_NoOverlappingApprovedRequests() {
        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Collections.emptyList());

        boolean isValid = leaveRequestServiceImpl.isLeaveRequestValid(leaveRequest);

        assertTrue(isValid);
    }

    @Test
    public void testIsLeaveRequestValid_OverlappingApprovedRequests() {
        LeaveRequest existingRequest = new LeaveRequest(LocalDate.of(2024, 10, 17), LocalDate.of(2024, 10, 19), "Sick Leave", LeaveRequestStatus.APPROVED, employee);

        when(leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId())).thenReturn(Arrays.asList(existingRequest));

        boolean isValid = leaveRequestServiceImpl.isLeaveRequestValid(leaveRequest);

        assertFalse(isValid);
    }



}
