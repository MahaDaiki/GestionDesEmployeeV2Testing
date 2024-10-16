package controllers;

import DAO.DaoImplementation.AuthenticationDAOImpl;
import DAO.DaoImplementation.CandidateDAOImpl;
import configs.JpaUtil;

import models.Candidate;
import services.serviceImplementations.AuthenticationServiceImpl;
import services.serviceImplementations.CandidateServiceImpl;
import services.serviceInterfaces.CandidateServiceInt;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/Candidate")
public class CandidatServlet extends HttpServlet {
    private CandidateServiceInt candidateService;

    @Override
    public void init() throws ServletException {
        EntityManager entityManager = JpaUtil.getEntityManager();
        CandidateDAOImpl CandidateDao = new CandidateDAOImpl();
        candidateService = new CandidateServiceImpl( CandidateDao );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "findAll":
                findAllCandidates(request, response);
                break;
            case "findById":
                findCandidateById(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void findAllCandidates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidate> candidates = candidateService.findAllCandidates();
        request.setAttribute("candidates", candidates);
        request.getRequestDispatcher("candidateList.jsp").forward(request, response);
    }

    private void findCandidateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Candidate candidate = candidateService.findCandidateById(id);
        request.setAttribute("candidate", candidate);
        request.getRequestDispatcher("candidateDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "update":
                updateCandidate(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void updateCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long updateId = (Long) request.getSession().getAttribute("candidateId");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");
        LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));
        String skills = request.getParameter("skills");
        String socialSecurityNum = request.getParameter("socialSecurityNum");

        Candidate updatedCandidate = new Candidate(name, email, phoneNumber, password, birthdate, skills, socialSecurityNum);
        updatedCandidate.setId(updateId);

        candidateService.updateCandidate(updatedCandidate);
        response.sendRedirect("candidateList.jsp");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long candidateId = (Long) request.getSession().getAttribute("candidateId");
        Candidate candidate = candidateService.findCandidateById(candidateId);
        request.setAttribute("candidate", candidate);
        request.getRequestDispatcher("updateCandidate.jsp").forward(request, response);
    }
}
