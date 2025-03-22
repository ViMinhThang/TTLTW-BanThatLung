package com.thomas.controller;

import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "filterController", value = "/filter")
public class filterController extends HttpServlet {
    //khi bấm submit thì nó sẽ trả về type rồi dựa vào đó để lấy ra list cần dùng
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        ProductService productService = new ProductService();
//
//        String type = session.getAttribute("type").toString();
//        request.setAttribute("type", type);
//        double min = Double.parseDouble(request.getParameter("minPrice"));
//        double max = Double.parseDouble(request.getParameter("maxPrice"));
//        String mainImage = "/assets/images/allProduct.png";
//        List<Belts> filteredList = new ArrayList<>();
//        if (type.equalsIgnoreCase("all")) {
//            filteredList = productService.find(null);
//            filteredList.forEach(b -> b.setBeltVariants(productService.findVariants(b.getId(), null, null, null)));
//            filteredList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productService.getVariantImages(v.getId()))));
//        }
//        if (type.equalsIgnoreCase("men")) {
//            filteredList = filteredList.stream().filter(b -> b.getGender().equals("")).collect(Collectors.toList());
//        }
//        if (type.equalsIgnoreCase("women")) {
//            filteredList = productService.getMaleOrFemaleAndMaterialProducts("F", "all");
//        }
//        if (type.equalsIgnoreCase("menLeather")) {
//            filteredList = productService.getMaleOrFemaleAndMaterialProducts("M", "da");
//        }
//        if (type.equalsIgnoreCase("menCanvas")) {
//            filteredList = productService.getMaleOrFemaleAndMaterialProducts("M", "canvas");
//        }
//        if (type.equalsIgnoreCase("womenLeather")) {
//            filteredList = productService.getMaleOrFemaleAndMaterialProducts("F", "da");
//        }
//        if (type.equalsIgnoreCase("menCanvas")) {
//            filteredList = productService.getMaleOrFemaleAndMaterialProducts("F", "canvas");
//        }
//        request.setAttribute("mainImage", mainImage);
//        pagingforPage(request, productService.filterProduct(filteredList, min, max));
//        request.getRequestDispatcher("/frontend/allProduct/allProduct1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void pagingforPage(HttpServletRequest request, List<Belts> beltsList) {
//        HttpSession session = request.getSession();
//        int currentPage = 1;
//        String pageParam = request.getParameter("page");
//        if (pageParam != null && !pageParam.trim().isEmpty()) {
//            try {
//                currentPage = Math.max(1, Integer.parseInt(pageParam.trim()));
//            } catch (NumberFormatException e) {
//                Logger.getLogger(getClass().getName()).warning("Invalid page parameter: " + pageParam);
//            }
//        }
//
//        int totalProduct = beltsList.size();
//        int itemPerPage = 12;
//        int totalPages = totalProduct / itemPerPage;
//        if (totalProduct % itemPerPage != 0) {
//            totalPages += 1;
//        }
//        currentPage = Math.min(currentPage, totalPages);
//        int startIndex = (currentPage - 1) * itemPerPage; // lấy sản phẩm tiếp theo trong danh sách
//        int endIndex = Math.min(startIndex + itemPerPage, totalProduct);
//        List<Belts> beltsForPage = startIndex < totalProduct // tạo ra 1 list mới để chứa những sản phẩm kế tiếp trong trang hiện tại
//                ? beltsList.subList(startIndex, endIndex)
//                : new ArrayList<>();
//
//        request.setAttribute("totalProduct", totalProduct);
//        request.setAttribute("totalPages", totalPages);
//        request.setAttribute("currentPage", currentPage);
//        session.setAttribute("beltsList", beltsForPage);
    }
}