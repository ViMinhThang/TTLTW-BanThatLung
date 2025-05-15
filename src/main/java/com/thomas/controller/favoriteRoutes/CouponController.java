package com.thomas.controller.favoriteRoutes;

import com.thomas.dao.model.CartItem;
import com.thomas.dao.model.Coupon;
import com.thomas.services.UploadCouponService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

@WebServlet(name = "CouponController", value = "/coupon")
public class CouponController extends HttpServlet {
    UploadCouponService uploadCouponService = new UploadCouponService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        DecimalFormat formatter = new DecimalFormat("#,###.000", symbols);
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        String coupon = request.getParameter("code");
        if (coupon != null && cart != null) {
            Coupon cp = uploadCouponService.getCouponByCode(coupon);
            double grandTotal = 0;
            for (CartItem ci : cart.values()) {
                grandTotal += (ci.getPrice() * ci.getQuantity()) + (ci.getQuantity() * 15.000);
            }
            grandTotal = grandTotal - (grandTotal * (cp.getDiscountRate() / 100));
            String formattedGrandTotal = formatter.format(grandTotal).replace(",", ".");
            session.setAttribute("appliedCoupon", cp);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.write("{");
                out.write("\"grandTotal\": " + formattedGrandTotal);
                out.write("}");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

