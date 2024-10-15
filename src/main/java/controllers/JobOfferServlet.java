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
import java.util.List;

@WebServlet("/createJobOffer")
public class JobOfferServlet extends HttpServlet {
    private JobOfferServiceImpl jobOfferService = new JobOfferServiceImpl();

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            Recruiter recruiter = (Recruiter) session.getAttribute("user");

            if (recruiter == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            switch (action) {
                case "create":
                    createJobOffer(request, response, recruiter);
                    break;
                case "modify":
                    modifyJobOffer(request, response, recruiter);
                    break;
                case "delete":
                    deleteJobOffer(request, response);
                    break;
                default:
                    response.sendRedirect("views/recruiterdashboard.jsp");
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            Recruiter recruiter = (Recruiter) session.getAttribute("user");

            if (recruiter == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            switch (action) {
                case "display":
                    displayJobOffers(request, response);
                    break;
                case "edit":
                    displayEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("views/recruiterdashboard.jsp");
            }
        }
   private void createJobOffer(HttpServletRequest request, HttpServletResponse response, Recruiter recruiter) throws IOException, ServletException {
            String jobTitle = request.getParameter("jobTitle");
            String description = request.getParameter("description");
            String requirements = request.getParameter("requirements");

            if (jobTitle == null || jobTitle.trim().isEmpty() ||
                    description == null || description.trim().isEmpty() ||
                    requirements == null || requirements.trim().isEmpty()) {
                request.setAttribute("errorMessage", "All fields are required!");
                request.getRequestDispatcher("views/createJobOfferForm.jsp").forward(request, response);
                return;
            }

            JobOffer jobOffer = new JobOffer(jobTitle, description, requirements, java.time.LocalDate.now(), recruiter);

            try {
                jobOfferService.createJobOffer(jobOffer);
                response.sendRedirect("views/recruiterdashboard.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error occurred while creating the job offer.");
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }

    private void modifyJobOffer(HttpServletRequest request, HttpServletResponse response, Recruiter recruiter) throws IOException, ServletException {
            Long jobId = Long.parseLong(request.getParameter("jobOfferId"));
            String jobTitle = request.getParameter("jobTitle");
            String description = request.getParameter("description");
            String requirements = request.getParameter("requirements");

            JobOffer existingJobOffer = jobOfferService.getJobOfferById(jobId);

            if (existingJobOffer != null && existingJobOffer.getRecruiter().getId().equals(recruiter.getId())) {
                existingJobOffer.setJobTitle(jobTitle);
                existingJobOffer.setDescription(description);
                existingJobOffer.setRequirements(requirements);

                try {
                    jobOfferService.updateJobOffer(existingJobOffer);
                    response.sendRedirect("views/recruiterdashboard.jsp");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Error occurred while modifying the job offer.");
                    request.getRequestDispatcher("views/error.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Job offer not found or you do not have permission to modify this offer.");
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }

        private void deleteJobOffer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            Long jobId = Long.parseLong(request.getParameter("jobOfferId"));

            try {
                jobOfferService.deleteJobOffer(jobId);
                response.sendRedirect("views/recruiterdashboard.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error occurred while deleting the job offer.");
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }

        private void displayJobOffers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String jobIdParam = request.getParameter("jobOfferId");
            if (jobIdParam != null && !jobIdParam.isEmpty()) {
                Long jobId = Long.parseLong(jobIdParam);
                JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
                request.setAttribute("jobOffer", jobOffer);
                request.getRequestDispatcher("views/jobOfferDetail.jsp").forward(request, response);
            } else {
                List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
                request.setAttribute("jobOffers", jobOffers);
                request.getRequestDispatcher("views/jobOffersList.jsp").forward(request, response);
            }
        }

        private void displayEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long jobId = Long.parseLong(request.getParameter("jobOfferId"));
            JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);

            if (jobOffer != null) {
                request.setAttribute("jobOffer", jobOffer);
                request.getRequestDispatcher("views/editJobOfferForm.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Job offer not found.");
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }
}
