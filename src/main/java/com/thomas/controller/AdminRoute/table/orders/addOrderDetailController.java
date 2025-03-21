package com.thomas.controller.AdminRoute.table.orders;

import com.thomas.dao.model.Belts;
import com.thomas.dao.model.Order;
import com.thomas.dao.model.OrderDetails;
import com.thomas.services.UploadOrderDetailService;
import com.thomas.services.UploadOrderService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "orderDetailController", value = "/add-Order-details")
public class addOrderDetailController extends HttpServlet {
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    ProductService productService = new ProductService();
    UploadOrderService uploadOrderService = new UploadOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<Belts> allBelts = productService.find(null);
        int beltId = 0;
        for (Belts b : allBelts) {
            if (b.getName().equals(productName)) {
                beltId = b.getId();
            }
        }

        OrderDetails newOrderDetail = new OrderDetails();
        newOrderDetail.setOrderId(orderId);
        newOrderDetail.setBeltId(beltId);
        newOrderDetail.setBeltName(productName);
        newOrderDetail.setQuantity(quantity);
        newOrderDetail.setPrice(price);
        Order order = uploadOrderService.getOrderById(orderId);
        order.setOrderTotal(order.getOrderTotal() + newOrderDetail.getPrice());
        uploadOrderService.updateOrder(order);
        boolean success = uploadOrderDetailService.addOrderDetails(newOrderDetail);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (success) {
            out.write("{");
            out.write("\"id\": " + newOrderDetail.getBeltId() + ",");
            out.write("\"beltName\": \"" + newOrderDetail.getBeltName() + "\",");
            out.write("\"quantity\": " + newOrderDetail.getQuantity() + ",");
            out.write("\"price\": \"" + String.format("%.2f", newOrderDetail.getPrice()) + "\"");
            out.write("}");
            out.flush();

        }
    }

}

