package com.thomas.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Belts implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private String gender;
    private LocalDateTime releaseDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
    private double discountRate;
    private String materialBelt;
    private List<BeltVariant> beltVariants;
    private int totalSold;

    public Belts() {

    }

    public Belts(Belts other) {
        this.id = other.id;
        this.name = other.name;
        this.price = other.price;

        // Sao chép danh sách beltVariants (nếu có)
        if (other.beltVariants != null) {
            this.beltVariants = other.beltVariants.stream()
                    .map(BeltVariant::new)
                    .collect(Collectors.toList());
        } else {
            this.beltVariants = new ArrayList<>();
        }
    }

    public Belts(int id, String name, String description, double price, String gender, LocalDateTime releaseDate, LocalDateTime createdDate, LocalDateTime updatedDate, int isDeleted, double discountRate, String materialBelt, List<BeltVariant> beltVariants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.gender = gender;
        this.releaseDate = releaseDate;
        this.createdAt = createdDate;
        this.updatedAt = updatedDate;
        this.isDeleted = isDeleted;
        this.discountRate = discountRate;
        this.materialBelt = materialBelt;
        this.beltVariants = beltVariants;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String getMaterialBelt() {
        return materialBelt;
    }

    public void setMaterialBelt(String materialBelt) {
        this.materialBelt = materialBelt;
    }

    public String getFormattedCreateDate() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedReleaseDate() {
        return releaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedUpdateDate() {
        return updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setBeltVariants(List<BeltVariant> beltVariants) {
        this.beltVariants = beltVariants;
    }

    public List<BeltVariant> getBeltVariants() {
        return beltVariants;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
