package com.thomas.controller.productDetails;

import com.thomas.dao.model.Category;
import com.thomas.dao.model.Belts;
import com.thomas.dao.model.User;
import com.thomas.services.UploadFavoriteService;
import com.thomas.services.ProductService;
import com.thomas.services.UploadReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productDetailsController", value = "/productDetails")
public class productDetailsController extends HttpServlet {
    ProductService productService = new ProductService();
    UploadReviewService uploadReviewService = new UploadReviewService();
    UploadFavoriteService uploadFavoriteService = new UploadFavoriteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        productService.saveBeltView(beltId);
        boolean isPurchasedBelt = false;
        if (user != null) {
            isPurchasedBelt = productService.isUserPurchased(beltId, user.getId(), variantId);

        }
        Belts belt = productService.find(beltId).get(0);
        belt.setBeltVariants(productService.findVariants(beltId, null, null, variantId));
        List<Category> beltCategory = productService.findCategory(beltId, variantId);
        int totalReview = uploadReviewService.getTotalReviewsCount(beltId);
        List<String> descBeltImage = productService.getAllDescImage(beltId);
        List<Belts> randomBelts = productService.getRandomBelts();
        for (Belts b : randomBelts) {
            b.setBeltVariants(productService.findVariants(b.getId(), null, null, null));
            b.getBeltVariants().forEach(v -> v.setImages(productService.getVariantImages(v.getId())));
        }
        List<Belts> beltViewCount = productService.getBeltByViewCount();
        for (Belts b : beltViewCount) {
            b.setBeltVariants(productService.findVariants(b.getId(), null, null, null));
            b.getBeltVariants().forEach(v -> v.setImages(productService.getVariantImages(v.getId())));
        }
        request.setAttribute("allVariant", productService.findVariants(beltId, null, null, null));
        request.setAttribute("variant", belt.getBeltVariants().get(0));
        request.setAttribute("isPurchasedBelt", isPurchasedBelt);
        request.setAttribute("beltViewCount", beltViewCount);
        request.setAttribute("randomBelts", randomBelts);
        request.setAttribute("descBeltImage", descBeltImage);
        request.setAttribute("totalReview", totalReview);
        request.setAttribute("beltCategory", beltCategory);
        request.setAttribute("belt", belt);
        request.getRequestDispatcher("/frontend/productDetail/productDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("postComment")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String content = request.getParameter("desc");
            int beltId = Integer.parseInt(request.getParameter("beltId"));
            if (uploadReviewService.createReview(rating, content, beltId, userId)) {
                response.sendRedirect("/productDetails?beltId=" + beltId);
            }
        }
    }
}

