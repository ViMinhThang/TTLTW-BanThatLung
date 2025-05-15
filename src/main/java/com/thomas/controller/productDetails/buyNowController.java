package com.thomas.controller.productDetails;

import com.thomas.dao.model.Belts;
import com.thomas.dao.model.CartItem;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "buyNowController", value = "/buyNow")
public class buyNowController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        double price = Double.parseDouble(request.getParameter("price"));
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        Belts belt = productService.find(beltId).get(0);
        belt.setBeltVariants(productService.findVariants(beltId, color, size, variantId));
        CartItem item = new CartItem(belt, 1, price, belt.getBeltVariants().get(0));
        cart.put(beltId, item);
        session.setAttribute("cart", cart);
        response.sendRedirect("/checkout");
    }
}

