package com.example.int202javassrpreexam.controller;

import com.example.int202javassrpreexam.model.Employee;
import com.example.int202javassrpreexam.repository.EmployeeRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Authentication", value = "/057/login")
public class Authentication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Employee> employeeOpt;

        if (employeeOpt.isEmpty()) {
            request.setAttribute("loginError", "This email is not exist!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        Employee employee = employeeOpt.get();
        // Hashed using this Argon2Factory.Argon2Types.ARGON2d type!
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
    }
}