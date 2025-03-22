package com.thomas.controller.AdminRoute.table.orders;

import com.thomas.dao.model.Order;
import com.thomas.dao.model.OrderDetails;
import com.thomas.services.UploadOrderDetailService;
import com.thomas.services.UploadOrderService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "orderDetailsController", value = "/admin/table/orders/details")
public class orderDetailsController extends HttpServlet {
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    UploadOrderService uploadOrderService = new UploadOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Order order = uploadOrderService.getOrderById(id);
        uploadOrderService.setAddressName(order);
        uploadOrderService.setPaymentName(order);
        uploadOrderService.setUserName(order);
        List<OrderDetails> orderDetailsList = uploadOrderDetailService.getAllOrderDetails(id);
        for (OrderDetails orderDetails : orderDetailsList) {
            uploadOrderDetailService.setBeltName(orderDetails);
        }
        request.setAttribute("orderDetailsList", orderDetailsList);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/frontend/AdminPage/OrderView/orderDetailsPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("delete")) {
            int orderItemId = Integer.parseInt(request.getParameter("orderItemId"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            if (request.getParameter("oneItem") != null) {
                uploadOrderService.deteleOrder(orderId);
                response.sendRedirect("/admin/table/orders");
            }
            OrderDetails orderDetails = uploadOrderDetailService.findOrderDetailById(orderItemId);
            if (orderDetails != null) {
                Order order = uploadOrderService.getOrderById(orderId);
                order.setOrderTotal(order.getOrderTotal() - orderDetails.getPrice());
                uploadOrderService.updateOrder(order);
                uploadOrderDetailService.deleteOrderDetail(orderItemId);
                response.sendRedirect("/admin/table/orders/details?id=" + orderId);
            }
        }


    }
}

