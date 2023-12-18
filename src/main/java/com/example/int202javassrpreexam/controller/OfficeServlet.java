package com.example.int202javassrpreexam.controller;

import com.example.int202javassrpreexam.model.Office;
import com.example.int202javassrpreexam.repository.OfficeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.example.int202javassrpreexam.constants.Constants;
import static com.example.int202javassrpreexam.constants.Constants.SERVLET_PATH;

@WebServlet(name = "OfficeServlet", value = SERVLET_PATH + "office")
public class OfficeServlet extends HttpServlet implements Constants {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfficeRepository officeRepository = new OfficeRepository();
        List<Office> offices = officeRepository.findAll();
        HttpSession session = request.getSession();
        session.setAttribute("offices", offices);
        getServletContext().getRequestDispatcher(VIEW_PATH + "/office.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}