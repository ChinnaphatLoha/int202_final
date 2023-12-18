package com.example.int202javassrpreexam.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.int202javassrpreexam.constants.Constants.DEFAULT_PATH;

@WebFilter(filterName = "AuthenticationFilter", servletNames = {"EmployeeServlet", "OfficeServlet"})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher(DEFAULT_PATH + "login").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
