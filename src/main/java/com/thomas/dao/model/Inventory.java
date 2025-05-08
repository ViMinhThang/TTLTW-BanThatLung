package com.thomas.dao.model;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable {
    private int id;
    private int beltId;
    private int variantId;
    private int stockQuantity;
    private String beltName;
    private List<String> variantName;

    public Inventory() {

    }

    public Inventory(int beltId, int variantId, int stockQuantity) {
        this.beltId = beltId;
        this.variantId = variantId;
        this.stockQuantity = stockQuantity;

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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeltName() {
        return beltName;
    }

    public void setBeltName(String beltName) {
        this.beltName = beltName;
    }

    public List<String> getVariantName() {
        return variantName;
    }

    public void setVariantName(List<String> variantName) {
        this.variantName = variantName;
    }
}
