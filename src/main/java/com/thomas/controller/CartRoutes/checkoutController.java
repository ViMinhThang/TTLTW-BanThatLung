package com.thomas.controller.CartRoutes;

import com.thomas.dao.model.*;
import com.thomas.services.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Map;

@WebServlet(name = "checkoutController", value = "/checkout")
public class checkoutController extends HttpServlet {
    UploadAddressService uploadAddressService = new UploadAddressService();
    UploadPaymentMethod uploadPaymentMethod = new UploadPaymentMethod();
    CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        List<CartItem> cartItemList = cartService.getCart(user.getId());
        Coupon cp = (Coupon) session.getAttribute("appliedCoupon");
        int cartSize = cartItemList.size();
        long totalPrice = 0;
        long grandTotal;
        for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        double discountRate = cp == null ? 0 : cp.getDiscountRate();
        long discountAmount = Math.round(totalPrice * (discountRate / 100));
        grandTotal = totalPrice - discountAmount;

        List<Address> userAddresses = uploadAddressService.getAddressList(user.getId());

        List<PaymentMethod> paymentMethods = uploadPaymentMethod.getPaymentMethods();

        if (userAddresses.isEmpty()) {
            String message = "Vui lòng thêm địa chỉ ở phần thiết lập cá nhân";
            request.setAttribute("messageAddress", message);
        } else {
            request.setAttribute("userAddresses", userAddresses);
        }


        request.setAttribute("paymentMethods", paymentMethods);
        request.setAttribute("cartSize", cartSize);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/frontend/cartPage/checkoutPage/checkoutPage.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


}

