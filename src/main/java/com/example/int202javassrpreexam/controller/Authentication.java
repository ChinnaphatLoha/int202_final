package com.example.int202javassrpreexam.controller;

import com.example.int202javassrpreexam.model.Employee;
import com.example.int202javassrpreexam.repository.EmployeeRepository;
import de.mkammerer.argon2.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

import static com.example.int202javassrpreexam.constants.Constants.DEFAULT_PATH;

@WebServlet(name = "Authentication", value = DEFAULT_PATH + "login")
public class Authentication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isBlank()) {
            request.setAttribute("loginError", "You must enter the email to proceed!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        EmployeeRepository employeeRepository = new EmployeeRepository();
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);

        if (employeeOpt.isEmpty()) {
            request.setAttribute("loginError", "This email is not exist!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        Employee employee = employeeOpt.get();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        char[] passwordArray = password.toCharArray();
        boolean isValid = argon2.verify(employee.getPassword(), passwordArray);
        if (!isValid) {
            request.setAttribute("loginError", "Invalid Password or Email");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", employee);
            response.sendRedirect(request.getContextPath());
        }
    }
}