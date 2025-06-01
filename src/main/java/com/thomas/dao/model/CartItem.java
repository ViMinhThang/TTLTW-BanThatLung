package com.thomas.dao.model;

import java.time.LocalDateTime;

public class CartItem {
    private int id;
    private int userId;
    private int beltId;
    private int variantId;
    private long price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    private Belts belt;
    private BeltVariant variant;
    private int quantity;

    public CartItem() {

    }

    public CartItem(Belts belt, int quantity, long price, BeltVariant variant) {
        this.belt = belt;
        this.quantity = quantity;
        this.price = price;
        this.variant = variant;
    }

    public Belts getBelt() {
        return belt;
    }

    public void setBelt(Belts belt) {
        this.belt = belt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public BeltVariant getVariant() {
        return variant;
    }

    public void setVariant(BeltVariant variant) {
        this.variant = variant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeltId() {
        return beltId;
    }

    public void setBeltId(int beltId) {
        this.beltId = beltId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
