package com.thomas.services;

import com.thomas.dao.ReviewDao;
import com.thomas.dao.model.Reviews;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UploadReviewService {
    ReviewDao reviewDao;

    public UploadReviewService() {
        reviewDao = new ReviewDao();
    }

    public List<Reviews> getReviews() {
        return reviewDao.getReviews();
    }

    public void setReviewerName(Reviews reviews) {
        if (reviews != null) {
            String reviewerName = reviewDao.getReviewerName(reviews.getUserId());
            reviews.setReviewerName(reviewerName);
        }
    }

    public boolean deleteReview(int reviewId) {
        return reviewDao.deleteReview(reviewId);
    }

    public Reviews findReview(int reviewId) {
        return reviewDao.getReview(reviewId);
    }

    public void setProductName(Reviews reviews) {
        if (reviews != null) {
            String reviewerName = reviewDao.findProductNameByReviewId(reviews.getId());
            reviews.setBeltName(reviewerName);
        }
    }

    public String findProductName(int reviewId) {
        return reviewDao.findProductNameByReviewId(reviewId);
    }

    public List<Reviews> getReviewsByBeltIdPagination(int beltId, int offset, int size, int variantId) {
        return reviewDao.getReviewsByBeltIdPagination(beltId, offset, size, variantId);
    }

    public boolean createReview(int rating, String content, int beltId, int userId, int variantId) {
        Reviews review = new Reviews();
        review.setBeltId(beltId);
        review.setUserId(userId);
        review.setContent(content);
        review.setReviewerStar(rating);
        review.setCreatedAt(LocalDateTime.now());
        review.setVariantId(variantId);
        return reviewDao.createReview(review);
    }

    public int getTotalReviewsCount(int beltId, int variantId) {
        return reviewDao.getAllReviewByIdSize(beltId, variantId);
    }
}
