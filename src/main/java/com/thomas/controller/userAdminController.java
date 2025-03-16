package com.thomas.controller;

import com.thomas.dao.model.User;
import com.thomas.services.MD5Service;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "userAdminController", value = "/admin/table/users")
public class userAdminController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ajaxHeader = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(ajaxHeader)) {
            String message = request.getParameter("message");

            if ("update".equals(message)) {
                try {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    User user = uploadUserService.getUser(userId);

                    // Respond with user data in JSON format
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter out = response.getWriter();
                    out.write("{");
                    out.write("\"userName\": \"" + user.getName() + "\",");
                    out.write("\"email\": \"" + user.getEmail() + "\",");
                    out.write("\"gender\": \"" + user.getGender() + "\",");
                    out.write("\"role\": " + user.getRole() + ",");
                    out.write("\"birthDate\": \"" + user.getDateOfBirth() + "\",");
                    out.write("\"phoneNumber\": \"" + user.getPhoneNumber() + "\",");
                    out.write("\"isDeleted\": \"" + user.getIsDeleted() + "\"");
                    out.write("}");
                    out.flush();
                    return;
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID or server error.");
                    return;
                }
            }
        }
        List<User> userList = uploadUserService.getAllUsers();
        request.setAttribute("userList", userList);
        System.out.println(request.getRequestURI().endsWith("users")?"1":"0");
        request.getRequestDispatcher("/frontend/AdminPage/allUser/allUser.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("delete")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            uploadUserService.deleteUser(userId);
            response.sendRedirect(request.getContextPath() + "/admin/table/users");
            return;
        }
        int userId = request.getParameter("userId") != null ? Integer.parseInt(request.getParameter("userId")) : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String userName = request.getParameter("userName");
        String email = request.getParameter("Email");
        String password = request.getParameter("password");
        String retype = request.getParameter("reTypePassword");
        if (message.equals("create")) {
            password = request.getParameter("password");
            retype = request.getParameter("reTypePassword");
        }
        String gender = request.getParameter("gender");
        int role = Integer.parseInt(request.getParameter("role"));
        String birthdayString = request.getParameter("birthDate");
        LocalDate brithDate = LocalDate.parse(birthdayString, formatter);
        long phone = Integer.parseInt(request.getParameter("phoneNumber"));
        int isDeleted = Integer.parseInt(request.getParameter("isDeleted"));
        if (message.equals("create")) {
            uploadUserService.saveUser(userName, email, MD5Service.hashPassword(password), gender, role, brithDate, phone, isDeleted);

        } else if (message.equals("update")) {
            uploadUserService.updateUser(userId, userName, email, gender, role, brithDate, phone, isDeleted);
        }


        response.sendRedirect("/admin/table/users");
    }
}

