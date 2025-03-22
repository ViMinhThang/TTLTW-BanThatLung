package com.thomas.controller.userInfoRoutes;

import com.thomas.dao.model.Address;
import com.thomas.services.UploadAddressService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addressController", value = "/address")
public class addressController extends HttpServlet {
    UploadAddressService uploadAddressService = new UploadAddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("setIsUse")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int addressId = Integer.parseInt(request.getParameter("addressId"));
            Address address = uploadAddressService.setIsUseAddressDisplay(addressId, userId);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String json = String.format(
                        "{\"addressCity\": \"%s\", \"addressStreet\": \"%s\"}",
                        address.getAddressCity().replace("\"", "\\\""),
                        address.getAddressStreet().replace("\"", "\\\"")
                );
                out.write(json);
            }
            response.setStatus(200);

        }
    }
}

