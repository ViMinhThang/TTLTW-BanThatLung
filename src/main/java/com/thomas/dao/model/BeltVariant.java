package com.thomas.dao.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BeltVariant implements java.io.Serializable {
    private int id;
    private int beltId;
    private String color;
    private String size;
    private int stockQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> images;
    private List<Category> categories;

    public BeltVariant() {

    }

    public BeltVariant(BeltVariant other) {
        this.id = other.id;
        this.color = other.color;
        this.size = other.size;
        this.stockQuantity = other.stockQuantity;

        // Sao chép danh sách ảnh
        this.images = new ArrayList<>(other.images);
    }

    public BeltVariant(int id, int beltId, String color, String size, int stockQuantity, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> images) {
        this.id = id;
        this.beltId = beltId;
        this.color = color;
        this.size = size;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeltId() {
        return this.beltId;
    }

    public void setBeltId(int beltId) {
        this.beltId = beltId;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

