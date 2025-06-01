package com.thomas.controller.productDetails;

import com.google.gson.Gson;
import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.model.Belts;
import com.thomas.dao.model.CartItem;
import com.thomas.dao.model.User;
import com.thomas.services.CartService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "buyNowController", value = "/buyNow")
public class buyNowController extends HttpServlet {
    ProductService productService = new ProductService();
    BeltVariantDao dao = new BeltVariantDao();
    CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        double price = Double.parseDouble(request.getParameter("price"));
        long longPrice = (long) price;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        cartService.deleteBuyNow(user.getId());
        if (cartService.checkInventory(beltId, variantId) < 0) {
            session.setAttribute("toastMessage", "Sản phẩm đã hết hàng");
            response.sendRedirect("/productDetails?beltId=" + beltId + "&variantId=" + variantId);
            return;
        }
        CartItem newItem = new CartItem();
        newItem.setUserId(user.getId());
        newItem.setBeltId(beltId);
        newItem.setVariantId(variantId);
        newItem.setQuantity(1);
        newItem.setPrice(longPrice);
        cartService.addToCart(newItem, true);
        response.sendRedirect("/checkout");
    }
}

