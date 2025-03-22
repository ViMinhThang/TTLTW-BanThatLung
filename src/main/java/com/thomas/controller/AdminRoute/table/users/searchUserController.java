package com.thomas.controller.AdminRoute.table.users;

import com.thomas.dao.model.User;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "searchUserController", value = "/search-users")
public class searchUserController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<User> users = uploadUserService.searchUser(query);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        StringBuilder jsonResponse = new StringBuilder("[");

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            jsonResponse.append("{");
            jsonResponse.append("\"id\":").append(user.getId()).append(",");
            jsonResponse.append("\"name\":\"").append(user.getName()).append("\"");
            jsonResponse.append("}");

            if (i < users.size() - 1) {
                jsonResponse.append(",");
            }
        }

        jsonResponse.append("]");

        out.write(jsonResponse.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


