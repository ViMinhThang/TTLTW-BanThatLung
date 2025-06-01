package com.thomas.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Purchases implements java.io.Serializable {
    private int id;
    private int supplierId;
    private int beltVariantId;
    private int quantity;
    private int beltId;
    private LocalDateTime purchaseDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String beltName;
    private String supplierName;

    public Purchases() {

    }

    public Purchases(int supplierId, int beltVariantId, int quantity, LocalDateTime purchaseDate, LocalDateTime createdAt, int beltId) {
        this.supplierId = supplierId;
        this.beltVariantId = beltVariantId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
        this.beltId = beltId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getBeltVariantId() {
        return this.beltVariantId;
    }

    public void setBeltVariantId(int beltVariantId) {
        this.beltVariantId = beltVariantId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createAt) {
        this.createdAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updateAt) {
        this.updatedAt = updateAt;
    }

    public String getBeltName() {
        return this.beltName;
    }

    public void setBeltName(String beltName) {
        this.beltName = beltName;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getBeltId() {
        return this.beltId;
    }

    public void setBeltId(int beltId) {
        this.beltId = beltId;
    }
}
