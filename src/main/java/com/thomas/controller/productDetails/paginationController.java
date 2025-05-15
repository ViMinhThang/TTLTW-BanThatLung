package com.thomas.controller.productDetails;

import com.thomas.dao.model.Belts;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "paginationController", value = "/pagination")
public class paginationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Belts> beltsList = (List<Belts>) session.getAttribute("beltsList");
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                currentPage = Math.max(1, Integer.parseInt(pageParam.trim()));
            } catch (NumberFormatException e) {
                Logger.getLogger(getClass().getName()).warning("Invalid page parameter: " + pageParam);
            }
        }

        int itemPerPage = 12;
        int totalProduct = beltsList.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalProduct / itemPerPage));
        currentPage = Math.min(currentPage, totalPages);
        int startIndex = (currentPage - 1) * itemPerPage; // lấy sản phẩm tiếp theo trong danh sách
        int endIndex = Math.min(startIndex + itemPerPage, totalProduct);
        List<Belts> beltsForPage = startIndex < totalProduct // tạo ra 1 list mới để chứa những sản phẩm kế tiếp trong trang hiện tại
                ? beltsList.subList(startIndex, endIndex)
                : new ArrayList<>();

        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        session.setAttribute("beltsList", beltsForPage);
        request.getRequestDispatcher("/frontend/allProduct/allProduct1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}