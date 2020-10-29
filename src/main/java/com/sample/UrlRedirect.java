package com.sample;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlRedirect extends HttpServlet {
    private static final String VALID_REDIRECT = "http://cwe.mitre.org/data/definitions/601.html";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // BAD: a request parameter is incorporated without validation into a URL redirect
        response.sendRedirect(request.getParameter("target"));
    }
}
