package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Reviews;
import org.jdbi.v3.core.Handle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public List<Reviews> getReviews() {
        String sql = "SELECT * FROM reviews";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Reviews.class)
                        .list()
        );
    }

    public String getReviewerName(int reviewerId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT u.name FROM users u JOIN reviews r ON u.id = r.userId WHERE r.userId = :userId";
            String reviewerName = h.createQuery(sql)
                    .bind("userId", reviewerId)
                    .mapTo(String.class)
                    .findFirst().orElse(null);
            return reviewerName;
        });

    }

    public Reviews getReview(int reviewId) {
        String sql = "SELECT * FROM reviews WHERE id = :reviewId";

        return JDBIConnect.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("reviewId", reviewId)
                        .mapToBean(Reviews.class)
                        .findOne()
                        .orElse(null)
        );
    }


    public boolean deleteReview(int reviewId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM reviews WHERE id = :reviewId";

            return h.createUpdate(sql)
                    .bind("reviewId", reviewId)
                    .execute() > 0;
        });
    }

    public String findProductNameByReviewId(int reviewId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT b.name " +
                    "FROM belts b " +
                    "JOIN reviews r ON b.id = r.beltId " +
                    "WHERE r.id = :reviewId";

            String productName = h.createQuery(sql)
                    .bind("reviewId", reviewId)
                    .mapTo(String.class)
                    .findOne().orElse(null);

            return productName;
        });
    }

    public int getAllReviewByIdSize(int beltId, int variantId) {
        String sql = "SELECT COUNT(*) FROM reviews WHERE beltId = :beltId AND variantId = :variantId";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("beltId", beltId)
                        .bind("variantId", variantId)
                        .mapTo(Integer.class)
                        .one()
        );
    }


    public boolean createReview(Reviews review) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO reviews (beltId,userId,variantId,content,ratingStar,createdAt) VALUES (:beltId,:userId,:variantId,:content,:ratingStar,:createdAt)";
            return h.createUpdate(sql)
                    .bind("beltId", review.getBeltId())
                    .bind("userId", review.getUserId())
                    .bind("content", review.getContent())
                    .bind("ratingStar", review.getReviewerStar())
                    .bind("variantId", review.getVariantId())
                    .bind("createdAt", review.getCreatedAt()).execute() > 0;
        });
    }

    public List<Reviews> getReviewsByBeltIdPagination(int beltId, int offset, int size, int variantId) {
        String sql = "SELECT * FROM reviews WHERE beltId = :beltId AND variantId=:variantId LIMIT :size OFFSET :offset";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("beltId", beltId)
                        .bind("size", size)
                        .bind("offset", offset)
                        .bind("variantId", variantId)
                        .mapToBean(Reviews.class)
                        .list()
        );
    }

}
