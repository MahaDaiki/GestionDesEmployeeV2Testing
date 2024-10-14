package controllers;

import DAO.DaoImplementation.LeaveRequestDAOImpl;
import configs.JpaUtil;
import models.LeaveRequest;


import enums.LeaveRequestStatus;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private LeaveRequestDAOImpl leaveRequestDAO;

    @Override
    public void init() throws ServletException {
        EntityManager entityManager = JpaUtil.getEntityManager();
        leaveRequestDAO = new LeaveRequestDAOImpl(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<LeaveRequest> leaveRequests = leaveRequestDAO.findAllLeaveRequests();
        request.setAttribute("leaveRequests", leaveRequests);
        request.getRequestDispatcher("views/leaveRequests.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String statusStr = request.getParameter("status");
        LeaveRequestStatus status = LeaveRequestStatus.valueOf(statusStr);

        leaveRequestDAO.updateLeaveRequestStatus(id, status);
        response.sendRedirect("leaveRequest");
    }
}
