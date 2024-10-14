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
}
