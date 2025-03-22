package com.thomas.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reviews implements java.io.Serializable {
    private int id;
    private int beltId;
    private int userId;
    private String content;
    private LocalDateTime createdAt;
    private int reviewerStar;
    private String reviewerName;
    private String beltName;
    private int variantId;

    public Reviews() {

    }

    public Reviews(int id, int beltId, int userId, String content, LocalDateTime createdAt, int reviewerStar, String reviewerName, String beltName, int variantId) {
        this.id = id;
        this.beltId = beltId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.reviewerStar = reviewerStar;
        this.reviewerName = reviewerName;
        this.beltName = beltName;
        this.variantId = variantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeltId() {
        return beltId;
    }

    public void setBeltId(int beltId) {
        this.beltId = beltId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getReviewerStar() {
        return reviewerStar;
    }

    public void setReviewerStar(int reviewerStar) {
        this.reviewerStar = reviewerStar;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getBeltName() {
        return beltName;
    }

    public void setBeltName(String beltName) {
        this.beltName = beltName;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }
}
