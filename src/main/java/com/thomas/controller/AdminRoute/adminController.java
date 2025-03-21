package com.thomas.controller.AdminRoute;

import com.thomas.services.UploadOrderService;
import com.thomas.services.UploadPaymentMethod;
import com.thomas.services.ProductService;
import com.thomas.services.UploadReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@WebServlet(name = "adminController", value = "/admin")
public class adminController extends HttpServlet {
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadReviewService uploadReviewService = new UploadReviewService();
    ProductService productService = new ProductService();
    UploadPaymentMethod uploadPaymentMethod = new UploadPaymentMethod();
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    DecimalFormat formatter = new DecimalFormat("#,###.000", symbols);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        int totalOrder = uploadOrderService.getOrderCount();
        double totalOrderMoney = uploadOrderService.getTotalOrderMoney();
        int reviewTotal = uploadReviewService.getReviews().size();
        int outofStockNumber = productService.outOfStockBelt().size();
        String formattedTotalOrderMoney = formatter.format(totalOrderMoney).replace(",", ".");

        request.setAttribute("totalOrder", totalOrder);
        request.setAttribute("totalOrderMoney", formattedTotalOrderMoney);
        request.setAttribute("reviewTotal", reviewTotal);
        request.setAttribute("outofStockNumber", outofStockNumber);
        request.getRequestDispatcher("/frontend/AdminPage/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}