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
        String message = request.getParameter("message");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        if (message.equals("promote")) {
            permissionService.promote(userId);
        } else if (message.equals("demote")) {
            permissionService.demote(userId);
        }


        response.sendRedirect("/admin/table/permission");
    }
}
