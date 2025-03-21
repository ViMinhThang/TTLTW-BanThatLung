package com.thomas.controller.AdminRoute.chart;

import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "chartPieAdminController", value = "/chart-pie")
public class chartPieAdminController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Belts> hotSellingProducts = productService.hotSelling();
        hotSellingProducts = hotSellingProducts.subList(hotSellingProducts.size() - 5, hotSellingProducts.size());
        String[] labels = new String[hotSellingProducts.size()];
        for (int i = 0; i < hotSellingProducts.size(); i++) {
            labels[i] = hotSellingProducts.get(i).getName();
        }
        int[] data = new int[hotSellingProducts.size()];
        for (int i = 0; i < hotSellingProducts.size(); i++) {
            data[i] = hotSellingProducts.get(i).getTotalSold();
        }
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{");
        jsonResponse.append("\"labels\": [");
        for (int i = 0; i < labels.length; i++) {
            jsonResponse.append("\"").append(labels[i]).append("\"");
            if (i < labels.length - 1) {
                jsonResponse.append(", ");
            }
        }
        jsonResponse.append("],");

        jsonResponse.append("\"data\": [");
        for (int i = 0; i < data.length; i++) {
            jsonResponse.append(data[i]);
            if (i < data.length - 1) {
                jsonResponse.append(", ");
            }
        }
        jsonResponse.append("]}");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

