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
    ProductService productService2 = new ProductService();
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    DecimalFormat formatter = new DecimalFormat("#,###.000", symbols);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            response.sendRedirect("/");
            return;
        }

        List<Order> userOrders = uploadOrderService.getAllOrdersByUserId(user.getId());

        for (Order order : userOrders) {
            uploadOrderDetailService.setOrderDetails(order);

            for (OrderDetails od : order.getOrderDetails()) {
                List<BeltVariant> beltVariants = productService.findVariants(od.getBeltId(), null, null, od.getVariantId());

                if (!beltVariants.isEmpty()) {
                    BeltVariant selectedVariant = beltVariants.get(0);
                    od.setBeltVariant(selectedVariant);
                    od.setBeltImages(productService.getVariantImages(selectedVariant.getId()));
                    od.setCategories(productService.findCategory(od.getBeltId(), selectedVariant.getId()));
                    uploadOrderDetailService.setBeltName(od);
                }
            }

            order.setShippingDate();
        }

        userOrders.sort((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()));

        request.setAttribute("orders", userOrders);
        request.getRequestDispatcher("/frontend/userInfoPage/orderView/ordersView.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

