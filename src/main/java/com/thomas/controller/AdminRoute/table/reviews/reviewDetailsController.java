package com.thomas.controller.AdminRoute.table.reviews;

import com.thomas.dao.model.Reviews;
import com.thomas.services.UploadReviewService;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "reviewDetailsController", value = "/admin/table/reviews/reviewDetail")
public class reviewDetailsController extends HttpServlet {
    UploadReviewService uploadReviewService = new UploadReviewService();
    UploadUserService uploadUserService = new UploadUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        Reviews review = uploadReviewService.findReview(reviewId);
        uploadReviewService.setReviewerName(review);
        review.setReviewerName(uploadUserService.getUser(review.getUserId()).getName());
        String beltName = uploadReviewService.findProductName(reviewId);
        request.setAttribute("beltName", beltName);
        request.setAttribute("review", review);
        request.getRequestDispatcher("/frontend/AdminPage/reviewDetailPage/reviewDetailsPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

