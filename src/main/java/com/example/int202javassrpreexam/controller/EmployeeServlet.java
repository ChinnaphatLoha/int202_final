package com.example.int202javassrpreexam.controller;

import com.example.int202javassrpreexam.model.Employee;
import com.example.int202javassrpreexam.repository.EmployeeRepository;
import com.example.int202javassrpreexam.repository.OfficeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.int202javassrpreexam.constants.Constants.DEFAULT_PATH;

@WebServlet(name = "EmployeeServlet", value = DEFAULT_PATH + "employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee loginEmployee = (Employee) request.getSession().getAttribute("user");
        String officeId = (request.getParameter("officeId") != null) ? request.getParameter("officeId") : loginEmployee.getOffice().getId();
        OfficeRepository officeRepository = new OfficeRepository();
        request.setAttribute("officeEmployee", officeRepository.getEmployeeList(officeId));
        request.setAttribute("officeId", officeId);
        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeIdParam = request.getParameter("employeeId");
        Integer employeeId = Integer.parseInt(employeeIdParam);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee deletedEmployee = employeeRepository.findById(employeeId);
        employeeRepository.delete(deletedEmployee);
        response.sendRedirect(request.getContextPath() + DEFAULT_PATH + "employee?officeId=" + request.getParameter("officeId"));
    }
}