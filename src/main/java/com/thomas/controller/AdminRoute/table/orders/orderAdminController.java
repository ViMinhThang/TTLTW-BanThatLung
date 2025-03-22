package com.thomas.controller.AdminRoute.table.orders;

import com.thomas.dao.model.Address;
import com.thomas.dao.model.Order;
import com.thomas.dao.model.OrderDetails;
import com.thomas.services.UploadAddressService;
import com.thomas.services.UploadOrderDetailService;
import com.thomas.services.UploadOrderService;
import com.thomas.services.UploadPaymentMethod;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "orderAdminController", value = "/admin/table/orders")
public class orderAdminController extends HttpServlet {
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    UploadPaymentMethod uploadPaymentMethod = new UploadPaymentMethod();
    UploadAddressService uploadAddressService = new UploadAddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = uploadOrderService.getAllOrders();
        for (Order order : orderList) {
            uploadOrderService.setUserName(order);
            uploadOrderService.setPaymentName(order);
            List<OrderDetails> orderDetailsList = uploadOrderDetailService.getAllOrderDetails(order.getId());
        }
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/frontend/AdminPage/allOrder/allOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String message = request.getParameter("message");
        if (message.equals("delete")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            uploadOrderService.deteleOrder(orderId);
        } else if (message.equals("create")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String paymentName = request.getParameter("paymentMethod");
            String orderDateString = request.getParameter("orderDate");
            LocalDate orderDate = LocalDate.parse(orderDateString, formatter);
            String addressStreet = request.getParameter("addressStreet");
            String addressCity = request.getParameter("addressCity");
            String orderState = request.getParameter("orderState");
            int isDeleted = Integer.parseInt(request.getParameter("isDeleted"));
            int paymentMethodId = uploadPaymentMethod.getPaymentMethodId(paymentName);
            uploadAddressService.createAddress(userId, addressCity, addressStreet);
            Address ad = uploadAddressService.getLatestAddress(userId, addressCity, addressStreet);
            uploadAddressService.setIsUseAddress(ad.getId(), userId);
            uploadOrderService.createOrder(userId, paymentMethodId, ad.getId(), orderDate, 1, orderState, isDeleted);
        }
        response.sendRedirect("/admin/table/orders");

    }
}

