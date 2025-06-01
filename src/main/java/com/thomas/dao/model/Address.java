package com.thomas.dao.model;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private int userId;
    private String fullName;
    private int phone;
    private int provinceId;
    private int districtId;
    private String wardId;
    private String addressDetail;
    private int isUse;
    private String provinceName;
    private String districtName;
    private String wardName;

    public Address() {

    }

    public Address(int userId, int provinceId, int districtId, String wardId, String addressDetail, String fullName, int phone) {
        this.userId = userId;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.addressDetail = addressDetail;
        this.fullName = fullName;
        this.phone = phone;
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

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardName() {
        return wardName;
    }
}