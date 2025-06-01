package com.thomas.controller.CartRoutes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thomas.dao.model.CartItem;
import com.thomas.dao.model.Belts;
import com.thomas.dao.model.User;
import com.thomas.services.CartService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.LocalDateTimeAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartController", value = "/Cart")
public class CartController extends HttpServlet {
    ProductService productService = new ProductService();
    CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        List<CartItem> cartItemList = cartService.getCart(user.getId());
        if (request.getParameter("message") != null && request.getParameter("message").equals("getCartSize")) {
            Gson gson = new Gson();
            String json = gson.toJson(cartItemList.size(), Integer.class);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }
        for (CartItem cartItem : cartItemList) {
            cartItem.setBelt(productService.find(cartItem.getBeltId()).get(0));
            cartItem.setVariant(productService.findVariant(cartItem.getBeltId(), cartItem.getVariantId(), null, null));
            cartItem.getVariant().setCategoryList(productService.findCategory(cartItem.getBeltId(), cartItem.getVariantId()));
            cartItem.getVariant().setColor(productService.findColorNameById(cartItem.getVariant().getColorId()));
            cartItem.getVariant().setSize(productService.findSizeNameById(cartItem.getVariant().getSizeId()));
        }
        List<Belts> suggestionBelts = productService.getRandomBelts();

        double totalPrice = 0;
        for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        double grandTotal = totalPrice;

        if (cartItemList.isEmpty()) {
            grandTotal = 0;
        }

        request.setAttribute("suggestionBelts", suggestionBelts);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("cartSize", cartService);
        request.setAttribute("cartItemList", cartItemList);

        request.getRequestDispatcher("/frontend/cartPage/cartPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        Gson gson = new Gson();

        if (user == null) {
            response.setStatus(401);
            return;
        }

        int userId = user.getId();

        if ("add".equals(message)) {
            double price = Double.parseDouble(request.getParameter("price").replaceAll(",", ""));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String color = request.getParameter("color");
            String size = request.getParameter("size");
            int beltId = Integer.parseInt(request.getParameter("beltId"));
            int variantId = Integer.parseInt(request.getParameter("variantId"));
            if (cartService.checkInventory(beltId, variantId) < 0) {
                String json = gson.toJson("Sản phẩm đã hết số lượng bạn chọn");
                response.setStatus(403);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
            List<CartItem> cartItemList = cartService.getCart(userId);
            CartItem existingItem = null;
            for (CartItem item : cartItemList) {
                if (item.getBeltId() == beltId && item.getVariantId() == variantId) {
                    existingItem = item;
                    break;
                }
            }

            if (existingItem != null) {
                int newQuantity = existingItem.getQuantity() + quantity;
                cartService.updateQuantity(userId, variantId, beltId, newQuantity);
            } else {
                CartItem newItem = new CartItem();
                newItem.setUserId(userId);
                newItem.setBeltId(beltId);
                newItem.setVariantId(variantId);
                newItem.setQuantity(quantity);
                newItem.setPrice((long) price);
                cartService.addToCart(newItem, false);
            }

            cartItemList = cartService.getCart(userId);
            for (CartItem item : cartItemList) {
                item.setBelt(productService.find(item.getBeltId()).get(0));
                item.setVariant(productService.findVariant(item.getBeltId(), item.getVariantId(), null, null));
            }

            String json = gson.toJson(cartItemList.size(), Integer.class);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else if ("remove".equals(message)) {
            int beltId = Integer.parseInt(request.getParameter("beltId"));
            int variantId = Integer.parseInt(request.getParameter("variantId"));

            cartService.removeFromCart(userId, variantId, beltId);

            List<CartItem> cartItemList = cartService.getCart(userId);
            for (CartItem cartItem : cartItemList) {
                cartItem.setBelt(productService.find(cartItem.getBeltId()).get(0));
                cartItem.setVariant(productService.findVariant(cartItem.getBeltId(), cartItem.getVariantId(), null, null));
            }
            gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            String json = gson.toJson(cartItemList.toArray(), CartItem[].class);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } else if ("update".equals(message)) {
            int beltId = Integer.parseInt(request.getParameter("beltId"));
            int variantId = Integer.parseInt(request.getParameter("variantId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            cartService.updateQuantity(userId, variantId, beltId, quantity);

            List<CartItem> cartItemList = cartService.getCart(userId);
            for (CartItem cartItem : cartItemList) {
                cartItem.setBelt(productService.find(cartItem.getBeltId()).get(0));
                cartItem.setVariant(productService.findVariant(cartItem.getBeltId(), cartItem.getVariantId(), null, null));
            }
            gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            String json = gson.toJson(cartItemList.toArray(), CartItem[].class);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } else {
            response.setStatus(400);
        }
    }

}

