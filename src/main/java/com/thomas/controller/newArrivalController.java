package com.thomas.controller;

import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "newArrivalController", value = "/newArrival")
public class newArrivalController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descPrice = request.getParameter("descPrice");
        List<Belts> newArrivalBelts = productService.find(null);
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        if (descPrice != null && !descPrice.trim().isEmpty()) {
            switch (descPrice.trim()) {
                case "increase":
                    newArrivalBelts.sort(Comparator.comparing(Belts::getPrice));
                    break;
                case "decrease":
                    newArrivalBelts.sort(Comparator.comparing(Belts::getPrice).reversed());
                    break;
                case "hotSelling":
                    newArrivalBelts = productService.getNewArrivals();
                    break;
            }
        }
        if (minPrice != null && maxPrice != null) {
            newArrivalBelts = productService.filterProduct(newArrivalBelts, Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        }
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                currentPage = Math.max(1, Integer.parseInt(pageParam.trim()));
            } catch (NumberFormatException e) {
                Logger.getLogger(getClass().getName()).warning("Invalid page parameter: " + pageParam);
            }
        }
        int itemsPerPage = 12;
        int totalItems = newArrivalBelts.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalItems / itemsPerPage));
        currentPage = Math.min(currentPage, totalPages);
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        List<Belts> beltsForPage = startIndex < totalItems
                ? newArrivalBelts.subList(startIndex, endIndex)
                : new ArrayList<>();
        request.setAttribute("beltsForPage", beltsForPage);
        request.setAttribute("totalBelt", newArrivalBelts.size());
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("descPrice", descPrice);
        request.getRequestDispatcher("/frontend/newArrivalPage/newArrivalPage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}