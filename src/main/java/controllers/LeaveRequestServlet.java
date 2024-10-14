
package controllers;

import DAO.DaoImplementation.LeaveRequestDAOImpl;
import configs.JpaUtil;
import models.Employee;
import models.LeaveRequest;
import enums.LeaveRequestStatus;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/leaveRequest")
    public class LeaveRequestServlet extends HttpServlet {

        private LeaveRequestDAOImpl leaveRequestDAO;

        @Override
        public void init() throws ServletException {
            EntityManager entityManager = JpaUtil.getEntityManager();
            leaveRequestDAO = new LeaveRequestDAOImpl(entityManager);
        }



        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("user");

            if (employee == null) {
                response.sendRedirect("auth?action=login");
                return;
            }

            // Retrieve form data
            String reason = request.getParameter("reason");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateStr , formatter);
            LocalDate endDate = LocalDate.parse(endDateStr , formatter);
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setEmployee(employee);
            leaveRequest.setReason(reason);
            leaveRequest.setStartDate(startDate);
            leaveRequest.setEndDate(endDate);
            leaveRequest.setStatus(LeaveRequestStatus.PENDING);

            System.out.println("leave request : " +leaveRequest);


            leaveRequestDAO.createLeaveRequest(leaveRequest);


            response.sendRedirect("auth?action=dashboardemployee");
        }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");

        if (employee == null) {
            response.sendRedirect("auth?action=login");
            return;
        }

        List<LeaveRequest> leaveRequests = leaveRequestDAO.findLeaveRequestsByEmployeeId(employee.getId());
        request.setAttribute("leaveRequests", leaveRequests);

        request.getRequestDispatcher("views/myleaveRequests.jsp").forward(request, response);
    }
    }

