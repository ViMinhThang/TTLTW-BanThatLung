package com.thomas.controller.CartRoutes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/momo-notify")
public class MomoNotify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        JSONObject jsonObject = new JSONObject(requestBody.toString());
        String orderId = jsonObject.getString("orderId");
        String resultCode = jsonObject.getString("resultCode");

        if ("0".equals(resultCode)) {
            System.out.println("Thanh toán thành công! Order ID: " + orderId);
        } else {
            System.out.println("Thanh toán thất bại!");
        }

        response.getWriter().write("{\"message\": \"Received\"}");
    }
}
