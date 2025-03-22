package com.thomas.controller.productDetails;

import com.thomas.dao.model.Reviews;
import com.thomas.services.UploadReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ReviewController", value = "/getReviews")

public class ReviewController extends HttpServlet {
    UploadReviewService uploadReviewService = new UploadReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        //offset để skip bao nhiêu row trong csdl
        int offset = (page - 1) * size;

        List<Reviews> reviews = uploadReviewService.getReviewsByBeltIdPagination(beltId, offset, size, variantId);
        for (Reviews review : reviews) {
            uploadReviewService.setReviewerName(review);
        }
        int totalReviews = uploadReviewService.getTotalReviewsCount(beltId,variantId);

        String reviewsHTML = generateReviewsHTML(reviews);

        int totalPages = (int) Math.ceil((double) totalReviews / size);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write("{");
        out.write("\"reviewsHTML\": \"" + reviewsHTML + "\",");
        out.write("\"totalPages\": " + totalPages);
        out.write("}");
    }

    private String generateReviewsHTML(List<Reviews> reviews) {
        StringBuilder html = new StringBuilder();
        for (Reviews review : reviews) {
            html.append("<div class='d-flex flex-column'>");
            html.append("<div class='rating-card'>");
            html.append("<div class='star-rating animated-stars'>");
            for (int i = 5; i >= 1; i--) {
                if (i <= review.getReviewerStar()) {
                    html.append("<label class='bi bi-star-fill text-warning'></label>");
                } else {
                    html.append("<label class='bi bi-star'></label>");
                }
            }

            html.append("</div></div>");
            html.append("<div class='content_review mt-2'>");
            html.append("<p class='mb-1'>" + review.getContent() + "</p>");
            html.append("</div>");
            html.append("<div class='d-flex author_review mt-0 border-bottom'>");
            html.append("<p class='fw-light fs-6 text-decoration-underline'>" + review.getReviewerName() + "</p>");
            html.append("<p class='fw-light fs-6 ms-2'>" + review.getCreatedAt() + "</p>");
            html.append("</div></div>");
        }
        return html.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

