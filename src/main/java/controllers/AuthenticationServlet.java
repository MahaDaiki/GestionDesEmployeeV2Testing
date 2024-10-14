package controllers;

import DAO.DaoImplementation.AuthenticationDAOImpl;
import configs.JpaUtil;
import models.Admin;
import models.Candidate;
import models.Employee;
import models.Recruiter;
import services.serviceImplementations.AuthenticationServiceImpl;
import services.serviceInterfaces.AuthenticationServiceInt;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {

    private AuthenticationServiceImpl authenticationService;

    @Override
    public void init() throws ServletException {
        EntityManager entityManager = JpaUtil.getEntityManager();
        AuthenticationDAOImpl authenticationDao = new AuthenticationDAOImpl(entityManager);
        authenticationService = new AuthenticationServiceImpl(authenticationDao);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("message", "Unknown action");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            default:
                request.setAttribute("message", "Unknown action");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("login")) {
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "dashboardadmin":
                handleAdminDashboard(request, response);
                break;
            case "dashboardemployee":
                handleEmployeeDashboard(request, response);
                break;
            case "dashboardrecruiter":
                handleRecruiterDashboard(request, response);
                break;
            case "dashboardcandidate":
                handleCandidateDashboard(request, response);
                break;
            default:
                request.setAttribute("message", "Unknown action");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = authenticationService.loginAsAdmin(email, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            session.setAttribute("role", "ADMIN");

            response.sendRedirect("views/admindashboard.jsp");
            return;
        }
        Employee employee = authenticationService.loginAsEmployee(email, password);
        if (employee != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", employee);
            session.setAttribute("role", "EMPLOYEE");
            response.sendRedirect("auth?action=dashboardemployee");
            return;
        }

        Recruiter recruiter = authenticationService.loginAsRecruiter(email, password);
        if (recruiter != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", recruiter);
            session.setAttribute("role", "RECRUITER");
            response.sendRedirect("auth?action=dashboardrecruiter");
            return;
        }

        Candidate candidate = authenticationService.loginAsCandidate(email, password);
        if (candidate != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", candidate);
            session.setAttribute("role", "CANDIDATE");
            response.sendRedirect("auth?action=dashboardcandidate");
            return;
        }

        request.setAttribute("message", "Invalid credentials");
        request.getRequestDispatcher("views/login.jsp").forward(request, response);
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }


    private void handleAdminDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && "ADMIN".equals(session.getAttribute("role"))) {
            Admin admin = (Admin) session.getAttribute("user");
            request.setAttribute("user", admin);
            request.getRequestDispatcher("views/admindashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Access Denied. Admins only.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

    private void handleEmployeeDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && "EMPLOYEE".equals(session.getAttribute("role"))) {
            Employee employee = (Employee) session.getAttribute("user");
            request.setAttribute("user", employee);
            request.getRequestDispatcher("views/employeedashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Access Denied. Employees only.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

    private void handleRecruiterDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && "RECRUITER".equals(session.getAttribute("role"))) {
            Recruiter recruiter = (Recruiter) session.getAttribute("user");
            request.setAttribute("user", recruiter);
            request.getRequestDispatcher("views/recruiterdashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Access Denied. Recruiters only.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }


    private void handleCandidateDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && "CANDIDATE".equals(session.getAttribute("role"))) {
            Candidate candidate = (Candidate) session.getAttribute("user");
            request.setAttribute("user", candidate);
            request.getRequestDispatcher("views/candidatedashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Access Denied. Candidates only.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

}
