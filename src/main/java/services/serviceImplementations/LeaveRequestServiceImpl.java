package services.serviceImplementations;

import DAO.DaoImplementation.LeaveRequestDAOImpl;
import enums.LeaveRequestStatus;
import models.LeaveRequest;
import services.serviceInterfaces.LeaveRequestServiceInt;

import java.util.List;

public class LeaveRequestServiceImpl implements LeaveRequestServiceInt {
    private LeaveRequestDAOImpl LeaveRequestDao;
    public LeaveRequestServiceImpl() {
        this.LeaveRequestDao = LeaveRequestDao;
    }


    @Override
    public void createLeaveRequest(LeaveRequest leaveRequest) {
        List<LeaveRequest> existingRequests = LeaveRequestDao.findLeaveRequestsByEmployeeId(leaveRequest.getEmployee().getId());

        for (LeaveRequest existingRequest : existingRequests) {
            if (existingRequest.getStatus() == LeaveRequestStatus.APPROVED &&
                    ((leaveRequest.getStartDate().isBefore(existingRequest.getEndDate()) &&
                            leaveRequest.getEndDate().isAfter(existingRequest.getStartDate())))) {
                throw new IllegalArgumentException("The leave request dates overlap with existing approved requests.");
            }
        }
        leaveRequest.setStatus(LeaveRequestStatus.PENDING);
        LeaveRequestDao.createLeaveRequest(leaveRequest);
    }

    @Override
    public LeaveRequest findLeaveRequestById(Long id) {
        return LeaveRequestDao.findLeaveRequestById(id);
    }

    @Override
    public List<LeaveRequest> findAllLeaveRequests() {
        return LeaveRequestDao.findAllLeaveRequests();
    }

    @Override
    public void updateLeaveRequestStatus(Long id, LeaveRequestStatus status) {
            LeaveRequestDao.updateLeaveRequestStatus(id, status);
    }

    @Override
    public List<LeaveRequest> findLeaveRequestsByEmployeeId(Long employeeId) {
        return LeaveRequestDao.findLeaveRequestsByEmployeeId(employeeId);
    }
    public boolean isLeaveRequestValid(LeaveRequest leaveRequest) {
        List<LeaveRequest> existingRequests = LeaveRequestDao.findLeaveRequestsByEmployeeId(leaveRequest.getEmployee().getId());
        for (LeaveRequest existingRequest : existingRequests) {
            if (existingRequest.getStatus() == LeaveRequestStatus.APPROVED) {
                if (areDatesOverlapping(leaveRequest, existingRequest)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areDatesOverlapping(LeaveRequest newRequest, LeaveRequest existingRequest) {
        return !newRequest.getEndDate().isBefore(existingRequest.getStartDate()) &&
                !newRequest.getStartDate().isAfter(existingRequest.getEndDate());
    }
}
