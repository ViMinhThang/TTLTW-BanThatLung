package com.thomas.controller.userInfoRoutes;

import com.thomas.dao.model.Address;
import com.thomas.dao.model.User;
import com.thomas.services.UploadAddressService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "userAddressController", value = "/userAddress")
public class userAddressController extends HttpServlet {
    UploadAddressService uploadAddressService = new UploadAddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Address> userAddressList = uploadAddressService.getAddressList(userId);
        request.setAttribute("userAddressList", userAddressList);
        request.getRequestDispatcher("/frontend/userInfoPage/address/address.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        if (message.equals("create")) {
            String fullname = request.getParameter("fullname");
            int phone = Integer.parseInt(request.getParameter("phoneNumber"));
            int province = Integer.parseInt(request.getParameter("province"));
            int district = Integer.parseInt(request.getParameter("district"));
            String ward = request.getParameter("ward");
            String detail = request.getParameter("addressDetail");
            if (uploadAddressService.addAddress(new Address(user.getId(), province, district, ward, detail, fullname, phone))) {
                response.sendRedirect("/userAddress?userId=" + user.getId());
            }
        } else if (message.equals("setDefault")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int addressId = Integer.parseInt(request.getParameter("userAddressId"));
            uploadAddressService.setDefault(addressId, userId);
            response.sendRedirect("/userAddress?userId=" + user.getId());

        }
    }
}

