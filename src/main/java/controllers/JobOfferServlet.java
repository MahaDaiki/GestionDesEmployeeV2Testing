package controllers;

import models.JobOffer;
import models.Recruiter;
import services.serviceImplementations.JobOfferServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/createJobOffer")
public class JobOfferServlet extends HttpServlet {
    private JobOfferServiceImpl jobOfferService = new JobOfferServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Recruiter recruiter = (Recruiter) session.getAttribute("user");

        if (recruiter != null) {
            String jobTitle = request.getParameter("jobTitle");
            String description = request.getParameter("description");
            String requirements = request.getParameter("requirements");
            LocalDate publishDate = LocalDate.now();

            JobOffer jobOffer = new JobOffer(jobTitle, description, requirements, publishDate, recruiter);
            jobOfferService.createJobOffer(jobOffer);

            response.sendRedirect("views/recruiters.jsp");
        } else {
            response.sendRedirect("views/recruiters.jsp");
        }
    }
}
