package com.thomas.dao.model;

public class Favorite {
    private int id;
    private String beltId;
    private String userId;
    private String variantId;

    public Favorite() {

    }

    public Favorite(int id, String beltId, String userId, String variantId) {
        this.id = id;
        this.beltId = beltId;
        this.userId = userId;
        this.variantId = variantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeltId() {
        return beltId;
    }

    public void setBeltId(String beltId) {
        this.beltId = beltId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }
}
