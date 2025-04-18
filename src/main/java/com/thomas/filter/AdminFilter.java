//package com.thomas.filter; import com.thomas.dao.model.User;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//
//@WebFilter(filterName = "AdminFilter",urlPatterns = "/admin/*")
//public class AdminFilter implements Filter {
//
//    public void init(FilterConfig config) throws ServletException { }
//
//    public void destroy() { } @Override
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest res = (HttpServletRequest) request;
//        HttpServletResponse req = (HttpServletResponse) response;
//        HttpSession session = res.getSession(true);
//        User user = (User) session.getAttribute("auth");
//        System.out.println(user);
//        if (user == null || user.getRole() < 1) {
//            req.sendRedirect("/");
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//}