package com.thomas.controller.CartRoutes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/momo-return")
public class MomoReturn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String paymentMethodId = request.getParameter("paymentMethodId");
        String addressId = request.getParameter("addressId");
        String total = request.getParameter("total");
        String status = "Đang xử lý";
        String resultCode = request.getParameter("resultCode");

        if ("0".equals(resultCode)) {
            response.getWriter().write("Thanh toán thành công!");
        } else {
            response.getWriter().write("Thanh toán thất bại!");
        }

        request.setAttribute("resultCode", resultCode);
        request.setAttribute("userId", userId);
        request.setAttribute("total", total);
        request.setAttribute("status", status);
        request.setAttribute("resultCode", resultCode);

        request.getRequestDispatcher("/frontend/cartPage/checkoutPage/momo-return.jsp").forward(request, response);
    }
}
