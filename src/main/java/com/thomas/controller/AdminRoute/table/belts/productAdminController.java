package com.thomas.controller.AdminRoute.table.belts;

import com.thomas.dao.model.Belts;
import com.thomas.dao.model.User;
import com.thomas.services.PermissionService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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
        User user = (User) session.getAttribute("auth");
        if (message.equals("delete")) {
            int variantId = Integer.parseInt(request.getParameter("variantId"));
            int beltId = Integer.parseInt(request.getParameter("productId"));
            PRODUCT_SERVICE.deleteProductVariant(beltId, variantId, user.getId());
        } else if (message.equals("deleteRealVariant")) {
            int beltId = Integer.parseInt(request.getParameter("productId"));
            PRODUCT_SERVICE.deleteProduct(beltId, null, user.getId());
        }
        response.sendRedirect("/admin/table/belts");
    }
}
