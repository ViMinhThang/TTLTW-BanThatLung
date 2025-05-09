package com.thomas.dao.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BeltVariant implements java.io.Serializable {
    private int id;
    private int beltId;
    private int sizeId;
    private int colorId;
    private long price;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private String color;
    private String size;
    private String description;
    private List<String> images;
    private int StockQuantity;
    private List<Category> categoryList;

    public BeltVariant() {

    }

    public BeltVariant(BeltVariant other) {
        this.id = other.id;
        this.colorId = other.colorId;
        this.sizeId = other.sizeId;
        this.price = other.price;
        this.images = other.images != null ? new ArrayList<>(other.images) : null;
    }

    public BeltVariant(int beltId, int sizeId, int colorId, long price, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.beltId = beltId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.price = price;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
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

    public int getSizeId() {
        return this.sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getColorId() {
        return this.colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    public void setStockQuantity(int stockQuantity) {
        StockQuantity = stockQuantity;
    }

    public int getStockQuantity() {
        return this.StockQuantity;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return this.categoryList;
    }
}

