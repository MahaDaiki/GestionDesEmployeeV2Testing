package controllers;

import DAO.DaoImplementation.EmployeeDAOImpl;
import DAO.DaoImplementation.FamilyAllowanceDAOImpl;
import configs.JpaUtil;
import models.Employee;
import models.FamilyAllowance;
import services.serviceImplementations.EmployeeServiceImpl;
import services.serviceImplementations.FamilyAllowanceServiceImpl;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hibernate.internal.util.StringHelper.isEmpty;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeServiceImpl employeeService;
    private FamilyAllowanceServiceImpl familyAllowanceService;

    @Override
    public void init() {
        employeeService = new EmployeeServiceImpl( new EmployeeDAOImpl( ));


        familyAllowanceService = new FamilyAllowanceServiceImpl(new FamilyAllowanceDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                List<Employee> employees = employeeService.findAllEmployees();
                request.setAttribute("employees", employees);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/addEmployee.jsp");
                dispatcher.forward(request, response);
                break;
            case "edit":
                showEditEmployee(request, response);
                break;
            default:
//                showAllEmployees(request, response);
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.printf("action" + action);
        switch (action) {
            case "add":
              addEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
        }
    }

    protected void showAddEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        List<Employee> employees = employeeService.findAllEmployees();
//        request.setAttribute("employees", employees);

//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/addEmployee.jsp");
//        dispatcher.forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String password = request.getParameter("password");
        String socialSecurityNum = request.getParameter("socialSecurityNum");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        int leaveDays = Integer.parseInt(request.getParameter("leaveDays"));
        int childCount = Integer.parseInt(request.getParameter("childCount"));
        double salary = Double.parseDouble(request.getParameter("salary"));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate hiringDate = LocalDate.parse(request.getParameter("hiringDate"), formatter);
        LocalDate birthdate = LocalDate.parse(request.getParameter("birthDate"), formatter);

        if (isEmpty(name) || isEmpty(department) || isEmpty(position) || isEmpty(phone_number)
                || isEmpty(email) || isEmpty(password) || isEmpty(socialSecurityNum)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Input cannot be empty.");
            return;
        }

        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid email format.");
            return;
        }


        if (phone_number == null || !phone_number.matches("^\\d{10}$")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phone number must be 10 digits.");
            return;
        }

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone_number(phone_number);
        employee.setPassword(password);
        employee.setSocialSecurityNum(socialSecurityNum);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setHiringDate(hiringDate);
        employee.setSalary(salary);
        employee.setLeaveDays(leaveDays);
        employee.setChildCount(childCount);
        employee.setBirthdate(birthdate);
        System.out.println(employee);

       employeeService.createEmployee(employee);
        double totalSalary = familyAllowanceService.calculateFamilyAllowance(employee);


        FamilyAllowance familyAllowance = new FamilyAllowance(employee, totalSalary);
        familyAllowanceService. createFamilyAllowance(familyAllowance);
        System.out.println(familyAllowance);
        response.sendRedirect("employees?action=add");
    }


    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParm = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone_number = request.getParameter("phone_number");
        String socialSecurityNum = request.getParameter("socialSecurityNum");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        int leaveDays = Integer.parseInt(request.getParameter("leaveDays"));
        int childCount = Integer.parseInt(request.getParameter("childCount"));
        double salary = Double.parseDouble(request.getParameter("salary"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate hiringDate = LocalDate.parse(request.getParameter("hiringDate"), formatter);
        LocalDate birthdate = LocalDate.parse(request.getParameter("birthDate"), formatter);

        if (isEmpty(name) || isEmpty(department) || isEmpty(position) || isEmpty(phone_number) || isEmpty(email) || isEmpty(socialSecurityNum)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Input cannot be empty.");
            return;
        }

        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid email format.");
            return;
        }

        if (phone_number == null || !phone_number.matches("^\\d{10}$")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phone number must be 10 digits.");
            return;
        }

        if (idParm != null) {
            long id = Long.parseLong(idParm);

            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setPhone_number(phone_number);
            employee.setSocialSecurityNum(socialSecurityNum);
            employee.setDepartment(department);
            employee.setPosition(position);
            employee.setHiringDate(hiringDate);
            employee.setSalary(salary);
            employee.setBirthdate(birthdate);
            employee.setLeaveDays(leaveDays);
            employee.setChildCount(childCount);


            employeeService.updateEmployee(employee);
            response.sendRedirect("employees?action=add");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee details");
        }
    }

    private void showEditEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParm = request.getParameter("id");
        if (idParm != null) {
            long id = Long.parseLong(idParm);
            Employee employee = employeeService.findEmployeeById(id);
            if (employee != null) {
                request.setAttribute("employee", employee);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/editEmployee.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee details");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee details");
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParm = request.getParameter("id");
        if (idParm != null) {
            long id = Long.parseLong(idParm);
            employeeService.deleteEmployee(id);
            response.sendRedirect("employees?action=add");
        }
    }
//
//    private void showAllEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Employee> employees = employeeService.findAllEmployees();
//        request.setAttribute("employees", employees);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/index.jsp");
//        dispatcher.forward(request, response);
//    }
}
