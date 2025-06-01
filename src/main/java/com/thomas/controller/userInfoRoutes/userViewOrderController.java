package com.thomas.controller.userInfoRoutes;

import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Order;
import com.thomas.dao.model.OrderDetails;
import com.thomas.dao.model.User;
import com.thomas.services.UploadOrderDetailService;
import com.thomas.services.UploadOrderService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "userViewOrderController", value = "/viewOrders")
public class userViewOrderController extends HttpServlet {
    ProductService productService = new ProductService();
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    DecimalFormat formatter = new DecimalFormat("#,###.000", symbols);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        String orderId = request.getParameter("orderId");
        List<Order> orderList = uploadOrderService.getAllOrdersByUserId(user.getId());
        if(orderId != null) {
            List<OrderDetails> orderDetailsList = uploadOrderDetailService.getAllOrderDetails(Integer.parseInt(orderId));
            Order order = uploadOrderService.getOrderById(Integer.parseInt(orderId));
            request.setAttribute("listOrderDetail", orderDetailsList);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/frontend/userInfoPage/orderView/orderDetails/orderDetails.jsp").forward(request, response);
            return;
        }
        for (Order order : orderList) {
            uploadOrderService.setUserName(order);
            uploadOrderService.setPaymentName(order);
        }
        request.setAttribute("orders", orderList);
        request.getRequestDispatcher("/frontend/userInfoPage/orderView/ordersView.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

