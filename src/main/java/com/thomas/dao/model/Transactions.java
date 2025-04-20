package com.thomas.dao.model;

import java.time.LocalDateTime;

public class Transactions implements java.io.Serializable {
    private Integer id;
    private int beltId;
    private int beltVariantId;
    private String transactionType;
    private int quantity;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String beltName;

    public Transactions() {

    }

    public Transactions(int beltVariantId, String transactionType, int quantity, LocalDateTime transactionDate, LocalDateTime createdAt, int beltId) {
        this.beltVariantId = beltVariantId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.createdAt = createdAt;
        this.beltId = beltId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBeltVariantId() {
        return this.beltVariantId;
    }

    public void setBeltVariantId(int beltVariantId) {
        this.beltVariantId = beltVariantId;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
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

    public String getBeltName() {
        return this.beltName;
    }

    public void setBeltName(String beltName) {
        this.beltName = beltName;
    }

    public void setBeltId(int beltId) {
        this.beltId = beltId;
    }

    public int getBeltId() {
        return this.beltId;
    }

}
