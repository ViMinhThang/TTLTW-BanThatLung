package com.thomas.controller.AdminRoute.chart;

import com.thomas.dao.model.Order;
import com.thomas.services.UploadOrderService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "chartAdminController", value = "/chart-area")
public class chartAdminController extends HttpServlet {
    UploadOrderService uploadOrderService = new UploadOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] labels ={"thg 1", "thg 2", "thg 3", "thg 4", "thg 5", "thg 6", "thg 7", "thg 8", "thg 9", "thg 10", "thg 11", "thg 12"};
        List<Order> allOrders = uploadOrderService.getAllOrders();
        int[] data = new int[labels.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");

        Map<String, Double> monthlyTotals = allOrders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().format(formatter),
                        Collectors.summingDouble(Order::getOrderTotal)
                ));

        for (int i = 0; i < labels.length; i++) {
            data[i] = monthlyTotals.getOrDefault(labels[i], 0.0).intValue();
        }

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"labels\": [");
        for (int i = 0; i < labels.length; i++) {
            jsonBuilder.append("\"").append(labels[i]).append("\"");
            if (i < labels.length - 1) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("], ");
        jsonBuilder.append("\"data\": [");
        for (int i = 0; i < data.length; i++) {
            jsonBuilder.append(data[i]);
            if (i < data.length - 1) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("]");
        jsonBuilder.append("}");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonBuilder.toString());
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

