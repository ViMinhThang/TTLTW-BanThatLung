package com.thomas.controller.AdminRoute.table.belts;

import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Belts;
import com.thomas.dao.model.User;
import com.thomas.services.PermissionService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productAdminController", value = "/admin/table/belts")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class productAdminController extends HttpServlet {
    private static final String ULOAD_DIR = "uploads";
    private static final ProductService PRODUCT_SERVICE = new ProductService();
    private static final PermissionService PERMISSION_SERVICE = new PermissionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        boolean permissionToWrite = PERMISSION_SERVICE.checkPermission("ManageProducts", user.getId(), "write");
        boolean permissionToExecute = PERMISSION_SERVICE.checkPermission("ManageProducts", user.getId(), "execute");

        List<Belts> beltList = PRODUCT_SERVICE.find(null);

        request.setAttribute("beltList", beltList);
        request.setAttribute("permissionToWrite", permissionToWrite);
        request.setAttribute("permissionToExecute", permissionToExecute);
        request.getRequestDispatcher("/frontend/AdminPage/allProduct/allProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        HttpSession session = request.getSession();
        if (message.equals("create")) {
            String name = request.getParameter("productName");
            String gender = request.getParameter("gender");
            int isDeleted = Integer.parseInt(request.getParameter("isDeleted"));
            double discountRate = Double.parseDouble(request.getParameter("discountRate"));
            String material = request.getParameter("material");
            Belts belt = new Belts();
            belt.setName(name);
            belt.setGender(gender);
            belt.setIsDeleted(isDeleted);
            belt.setDiscountRate(discountRate);
            belt.setIsDeleted(isDeleted);
            belt.setMaterialBelt(material);
            belt.setReleaseDate(LocalDateTime.now());
            belt.setCreatedAt(LocalDateTime.now());
            belt.setUpdatedAt(LocalDateTime.now());
            PRODUCT_SERVICE.createBelt(belt);
        }
        if (message.equals("delete")) {
            int beltId = Integer.parseInt(request.getParameter("beltId"));
            PRODUCT_SERVICE.deleteProduct(beltId);
        }
        response.sendRedirect("/admin/table/belts");
    }
}
