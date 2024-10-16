package controllers;


import DAO.DaoImplementation.CandidateDAOImpl;
import DAO.DaoImplementation.CandidateJobOfferDAOImpl;
import DAO.DaoIntferfaces.CandidateJobOfferDAOInt;
import configs.JpaUtil;
import models.Candidate;
import models.CandidateJobOffer;
import models.JobOffer;
import enums.ApplicationStatus;
import services.serviceImplementations.CandidateJobOfferServiceImpl;
import services.serviceImplementations.CandidateServiceImpl;
import services.serviceInterfaces.CandidateJobOfferServiceInt;
import services.serviceInterfaces.CandidateServiceInt;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/CandidateJobOfferServlet")
public class CandidateJobOfferServlet extends HttpServlet {

    private CandidateServiceInt candidateService;
    private CandidateJobOfferServiceInt candidateJobOfferService;

    public void init() throws ServletException {
        EntityManager entityManager = JpaUtil.getEntityManager();
        CandidateDAOImpl candidateDao = new CandidateDAOImpl();
        CandidateJobOfferDAOInt candidateJobOfferDao = new CandidateJobOfferDAOImpl();
        candidateService = new CandidateServiceImpl(candidateDao);
        candidateJobOfferService = new CandidateJobOfferServiceImpl(candidateJobOfferDao);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "apply":
                applyForJob(request, response);
                break;
            case "update":
                updateCandidate(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "apply":
                applyForJob(request, response);
                break;
            case "update":
                updateCandidate(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void applyForJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long candidateId = (Long) request.getSession().getAttribute("candidateId");
        Long jobOfferId = Long.valueOf(request.getParameter("jobOfferId"));
        ApplicationStatus status = ApplicationStatus.valueOf(request.getParameter("applicationStatus"));

        Candidate candidate = candidateService.findCandidateById(candidateId);
        JobOffer jobOffer = new JobOffer();
        jobOffer.setJobOfferId(jobOfferId);

        CandidateJobOffer candidateJobOffer = new CandidateJobOffer(candidate, jobOffer, status, LocalDate.now());
        candidateJobOfferService.applyForJob(candidateJobOffer);

        response.sendRedirect("applicationSuccess.jsp");
    }

    private void updateCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long candidateId = (Long) request.getSession().getAttribute("candidateId");
        String skills = request.getParameter("skills");
        String socialSecurityNum = request.getParameter("socialSecurityNum");

        Candidate updatedCandidate = candidateService.findCandidateById(candidateId);
        updatedCandidate.setSkills(skills);
        updatedCandidate.setSocialSecurityNum(socialSecurityNum);

        candidateService.updateCandidate(updatedCandidate);
        response.sendRedirect("candidateList.jsp");
    }
}
