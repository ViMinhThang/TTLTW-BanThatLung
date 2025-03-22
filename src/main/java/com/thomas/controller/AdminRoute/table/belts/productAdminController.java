package com.thomas.controller.AdminRoute.table.belts;

import com.thomas.dao.model.Belts;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Belts> beltList = PRODUCT_SERVICE.find(null);
        request.setAttribute("beltList", beltList);
        request.getRequestDispatcher("/frontend/AdminPage/allProduct/allProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("delete")) {
            int variantId = Integer.parseInt(request.getParameter("variantId"));
            int beltId = Integer.parseInt(request.getParameter("productId"));
            PRODUCT_SERVICE.deleteProductVariant(beltId, variantId);
            response.sendRedirect(request.getContextPath() + "/admin/table/belts");
            return;
        } else if (message.equals("deleteRealVariant")) {
            int beltId = Integer.parseInt(request.getParameter("productId"));
            PRODUCT_SERVICE.deleteProduct(beltId, null);
        }


        response.sendRedirect("/admin/table/belts/createProduct");
    }
}
