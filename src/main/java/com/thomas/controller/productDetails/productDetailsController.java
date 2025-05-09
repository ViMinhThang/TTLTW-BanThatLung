package com.thomas.controller.productDetails;

import com.thomas.dao.model.BeltVariant;
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
import java.util.ArrayList;
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
        belt.setBeltVariant(productService.findVariant(beltId, variantId, null, null));
        List<Category> beltCategory = productService.findCategory(beltId, variantId);
        int totalReview = uploadReviewService.getTotalReviewsCount(beltId, variantId);
        List<Belts> randomBelts = productService.getRandomBelts();
        List<Belts> displayRandom = prepareDisplayBelts(randomBelts, productService);

        List<Belts> beltViewCounts = productService.getBeltByViewCount();
        List<Belts> displayBeltViewCount = prepareDisplayBelts(beltViewCounts, productService);

        List<BeltVariant> similarVariants = productService.similarVariants(belt.getId());
        for (BeltVariant beltVariant : similarVariants) {
            beltVariant.setColor(productService.findColorNameById(beltVariant.getColorId()));
            beltVariant.setSize(productService.findSizeNameById(beltVariant.getSizeId()));
            beltVariant.setImages(productService.getVariantImages(beltVariant.getId()));
        }


        request.setAttribute("similarVariants", similarVariants);
        request.setAttribute("belt", belt);
        request.setAttribute("isPurchasedBelt", isPurchasedBelt);
        request.setAttribute("beltViewCount", displayBeltViewCount.subList(0, 4));
        request.setAttribute("randomBelts", displayRandom.subList(0, 4));
        request.setAttribute("totalReview", totalReview);
        request.setAttribute("beltCategory", beltCategory);
        request.getRequestDispatcher("/frontend/productDetail/productDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("postComment")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String content = request.getParameter("desc");
            int beltId = Integer.parseInt(request.getParameter("beltId"));
            int variantId = Integer.parseInt(request.getParameter("variantId"));
            if (uploadReviewService.createReview(rating, content, beltId, userId, variantId)) {
                response.sendRedirect("/productDetails?beltId=" + beltId + "&variantId=" + variantId);
            }
        }
    }

    private List<Belts> prepareDisplayBelts(List<Belts> belts, ProductService productService) {
        List<Belts> displayList = new ArrayList<>();

        for (Belts belt : belts) {
            Integer[] variantIds = productService.getAllVariantId(belt.getId());

            for (int variantId : variantIds) {
                BeltVariant beltVariant = productService.findVariant(belt.getId(), variantId, null, null);
                beltVariant.setImages(productService.getVariantImages(beltVariant.getId()));
                beltVariant.setColor(productService.findColorNameById(beltVariant.getId()));
                beltVariant.setSize(productService.findSizeNameById(beltVariant.getId()));

                Belts b = new Belts(belt);
                b.setBeltVariant(beltVariant);
                b.setTotalSold(productService.getTotalSold(belt.getId()));

                displayList.add(b);
            }
        }

        return displayList;
    }

}

