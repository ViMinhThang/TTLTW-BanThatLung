package com.thomas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/changeLanguage")
public class LanguageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String lang = request.getParameter("lang");
        if (lang != null) {
            Locale locale;
            if (lang.equals("vi")) {
                locale = new Locale("vi", "VN");
            } else {
                locale = new Locale("en", "US");
            }
            request.getSession().setAttribute("lang", locale);
        }
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : "index.jsp");
    }
}

