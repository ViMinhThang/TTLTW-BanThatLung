package com.thomas.controller;

import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "allProductController", value = "/navigate")
public class NavigateController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String sort = request.getParameter("descPrice");
        List<Belts> sortedList = null;
        if (type.equals("all")) {
            String title = "Sản Phẩm";
            String bigTitle = "Tất cả Sản Phẩm";
            String mainImage = "/assets/images/allProduct.png";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);
        }
        if (type.equals("Male")) {
            String title = "Nam";
            String bigTitle = "Thắt Lưng Nam";
            String mainImage = "/assets/images/banner/Hero-Banner-Male-1.jpg";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);

        }
        if (type.equals("Female")) {
            String title = "Nữ";
            String bigTitle = "Thắt Lưng Nữ";
            String mainImage = "/assets/images/banner/Hero-Banner-Female-1.png";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);
        }
        if (type.equals("Male-Leather")) {
            String title = "Nam";
            String bigTitle = "Thắt Lưng Da Nam";
            String mainImage = "/assets/images/banner/Hero-Banner-Male-1.jpg";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);
        }
        if (type.equals("Male-Canvas")) {
            String title = "Nam Canvas";
            String bigTitle = "Thắt lưng Canvas Nam";
            String mainImage = "/assets/images/banner/Hero-Banner-Male-1.jpg";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);

        }
        if (type.equals("Female-Leather")) {
            String title = "Nữ Leather";
            String bigTitle = "Thắt lưng Da Nữ";
            String mainImage = "/assets/images/banner/Hero-Banner-Female-1.png";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);

        }
        if (type.equals("Female-Canvas")) {
            String title = "Nữ Canvas";
            String bigTitle = "Thắt lưng Canvas Nữ";
            String mainImage = "/assets/images/banner/Hero-Banner-Female-1.jpg";
            handleRoute(type, title, bigTitle, mainImage, minPrice, maxPrice, request, response, session, sort, sortedList);

        }
    }

    public void handleRoute(String type, String title, String bigTitle, String mainImage, String minPrice, String maxPrice, HttpServletRequest request, HttpServletResponse response, HttpSession session, String sort, List<Belts> sortedList) throws ServletException, IOException {
        request.setAttribute("title", title);
        request.setAttribute("bigTitle", bigTitle);
        request.setAttribute("mainImage", mainImage);
        List<Belts> listBelt = productService.hotSelling();
        if (!type.equals("all")) {
            String[] split = type.split("-");
            listBelt = listBelt.stream().filter(b -> b.getGender().equals(split[0])).collect(Collectors.toList());
            if (split.length == 2) {
                listBelt = listBelt.stream().filter(b -> b.getMaterialBelt().equals(split[1])).collect(Collectors.toList());
            }

        }
        if (minPrice != null && maxPrice != null) {
            listBelt = productService.filterProduct(listBelt, Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        }
        if (sort != null && !sort.isEmpty()) {
            listBelt = productService.sort(sort, listBelt);
            request.setAttribute("listBelt", sortedList);

        } else {
            request.setAttribute("listBelt", listBelt);
        }
        pagingforPage(request, listBelt);
        request.getRequestDispatcher("/frontend/allProduct/allProduct1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void pagingforPage(HttpServletRequest request, List<Belts> beltsList) {

        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                currentPage = Math.max(1, Integer.parseInt(pageParam.trim()));
            } catch (NumberFormatException e) {
                Logger.getLogger(getClass().getName()).warning("Invalid page parameter: " + pageParam);
            }
        }

        // Tính tổng số sản phẩm (totalProduct) với mỗi biến thể của belt được tính là một sản phẩm riêng biệt
        int totalProduct = beltsList.size();
        // Số sản phẩm trên mỗi trang
        int itemPerPage = 12;

        // Tính tổng số trang
        int totalPages = totalProduct / itemPerPage;
        if (totalProduct % itemPerPage != 0) {
            totalPages += 1;
        }

        // Đảm bảo currentPage không vượt quá tổng số trang
        currentPage = Math.min(currentPage, totalPages);

        // Xác định chỉ số bắt đầu và kết thúc của các sản phẩm trong trang hiện tại
        int startIndex = (currentPage - 1) * itemPerPage;
        int endIndex = Math.min(startIndex + itemPerPage, totalProduct);

        // Danh sách chứa các sản phẩm (cả belt và các biến thể của nó) hiển thị trên trang
        List<Belts> beltsForPage = new ArrayList<>();
        int count = 0; // Dùng để đếm số lượng sản phẩm đã được đưa vào trang
        for (Belts b : beltsList) {
            // Sau đó là các biến thể (beltVariants)
            if (count >= startIndex && count < endIndex) {
                beltsForPage.add(b);
                count++;
            }
        }

        // Cập nhật các thông tin phân trang vào request
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

    }

}