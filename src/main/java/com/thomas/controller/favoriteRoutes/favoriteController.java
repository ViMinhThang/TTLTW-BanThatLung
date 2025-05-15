package com.thomas.controller.favoriteRoutes;

import com.thomas.dao.model.Belts;
import com.thomas.services.UploadFavoriteService;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "favoriteController", value = "/favorite")
public class favoriteController extends HttpServlet {
    UploadFavoriteService uploadFavoriteService = new UploadFavoriteService();
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        if (request.getParameter("message") != null) {
            String message = request.getParameter("message");
            if (message.equals("getAllFavorites")) {
                int favoriteBelts = Math.max(uploadFavoriteService.getFavoritesBeltByUserId(userId).size(), 0);
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().write(String.valueOf(favoriteBelts));
                return;
            }
        }
        List<Belts> favoriteBelts = uploadFavoriteService.getFavoritesBeltByUserId(userId);
        request.setAttribute("favoriteBelts", favoriteBelts);
        request.getRequestDispatcher("/frontend/favoritePage/favoritePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        int userId = Integer.parseInt(request.getParameter("userId"));
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        switch (message) {
            case "addFavorite":
                if (uploadFavoriteService.addFavoriteByUserId(userId, beltId, variantId)) {
                    response.setStatus(200);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Something went wrong");
                }
                break;
            case "removeFavorite":
                if (uploadFavoriteService.deleteFavoriteBelt(userId, beltId,variantId)){
                response.setStatus(200);
            } else{
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println("Something went wrong");
            }
            break;
        }
    }
}


