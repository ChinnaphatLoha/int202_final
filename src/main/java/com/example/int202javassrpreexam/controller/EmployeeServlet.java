package com.example.int202javassrpreexam.controller;

import com.example.int202javassrpreexam.model.Employee;
import com.example.int202javassrpreexam.model.Office;
import com.example.int202javassrpreexam.repository.EmployeeRepository;
import com.example.int202javassrpreexam.repository.OfficeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "EmployeeServlet", value = "/057/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}