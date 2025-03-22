package com.thomas.controller.userInfoRoutes;

import com.thomas.dao.model.Address;
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
        for (Address address : userAddressList) {
            uploadAddressService.setUserName(address);
            uploadAddressService.setPhoneNumber(address);
        }
        request.setAttribute("userAddressList", userAddressList);
        request.getRequestDispatcher("/frontend/userInfoPage/address/address.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        int userId = Integer.parseInt(request.getParameter("userId"));
        int addressId = request.getParameter("userAddressId") != null ? Integer.parseInt(request.getParameter("userAddressId")) : 0;
        if (message.equals("setDefaultAddress")) {
            uploadAddressService.setIsUseAddress(addressId, userId);
        } else if (message.equals("updateAddress")) {
            String addressStreet = request.getParameter("AddressStreet");
            String addressCity = request.getParameter("AddressCity");
            uploadAddressService.updateAddress(addressId, addressStreet, addressCity);
        } else if (message.equals("delete")) {
            uploadAddressService.deleteAddress(addressId);
        } else if (message.equals("create")) {
            String addressStreet = request.getParameter("addressStreet");
            String addressCity = request.getParameter("addressCity");
            uploadAddressService.createAddress(userId, addressCity, addressStreet);
        }
        response.sendRedirect("/userAddress?userId=" + userId);

    }
}

