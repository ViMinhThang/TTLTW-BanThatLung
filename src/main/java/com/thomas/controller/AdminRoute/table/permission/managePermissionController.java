package com.thomas.controller.AdminRoute.table.permission;

import com.thomas.dao.model.Belts;
import com.thomas.dao.model.User;
import com.thomas.dao.model.UserPermissionView;
import com.thomas.services.PermissionService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "managePermissionController", value = "/admin/table/permission")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class managePermissionController extends HttpServlet {
    PermissionService permissionService = new PermissionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserPermissionView> userPermissionViews = permissionService.getAll();

        request.setAttribute("userPermissionViews", userPermissionViews);
        request.getRequestDispatcher("/frontend/AdminPage/allUserPermissions/allPermission.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        String message = request.getParameter("message");
        if (message.equals("promote")) {
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            permissionService.promote(userId, user.getId());
        } else if (message.equals("demote")) {
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            permissionService.demote(userId, user.getId());
        } else if (message.equals("addRole")) {
            String userEmail = request.getParameter("userEmail");
            String role = request.getParameter("role");
            permissionService.setRole(userEmail, role, user.getId());
        }

        response.sendRedirect("/admin/table/permission");
    }
}
