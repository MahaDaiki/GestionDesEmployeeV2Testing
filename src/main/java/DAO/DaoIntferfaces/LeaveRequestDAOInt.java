package DAO.DaoIntferfaces;

import enums.LeaveRequestStatus;
import models.LeaveRequest;

import java.util.List;

public interface LeaveRequestDAOInt {
    void createLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest findLeaveRequestById(Long id);
    List<LeaveRequest> findAllLeaveRequests();
    void updateLeaveRequestStatus(Long id, LeaveRequestStatus status);
    List<LeaveRequest> findLeaveRequestsByEmployeeId(Long employeeId);
}
