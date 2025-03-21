package com.thomas.controller.productDetails;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "searchController", value = "/search")
public class searchController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        String query = request.getParameter("q");
//
//        List<Belts> queryBelts = productService.searchProduct(query);
//
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonSerializer<LocalDate>)
//                        (localDate, type, jsonSerializationContext) ->
//                                new com.google.gson.JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)))
//                .create();
//        String json = gson.toJson(queryBelts);
//
//        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}