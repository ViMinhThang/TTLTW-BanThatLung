package com.thomas.controller.api.inventory;


import com.google.gson.Gson;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetColorsByNameAndSize", value = "/getColorsByNameAndSize")
public class GetColorsByNameAndSize extends HttpServlet {
    VariantService variantService = new VariantService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String size = request.getParameter("size");
        List<String> beltColor = variantService.getAllColorByNameBeltAndSize(name, size);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("colors", beltColor);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(jsonResponse);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}