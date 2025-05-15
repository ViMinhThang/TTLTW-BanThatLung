package com.thomas.dao.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private String email;
    private long phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String image;
    private int role;
    private LocalDateTime createAt;
    private int isDeleted;
    private int isActive;
    private LocalDateTime lastActivateAt;
    private String oauthProvider; // New field for OAuth provider
    private String oauthId;       // New field for OAuth ID

    public User() {
    }

    public User(String password, String name, String email, long phoneNumber, LocalDate dateOfBirth, String gender, String image, int role, LocalDateTime createAt, int isDeleted, int isActive, LocalDateTime lastActivateAt, String oauthProvider, String oauthId) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.image = image;
        this.role = role;
        this.createAt = createAt;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.lastActivateAt = lastActivateAt;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
    }

    // Getters and Setters for all fields including the new ones

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getLastActivateAt() {
        return lastActivateAt;
    }

    public void setLastActivateAt(LocalDateTime lastActivateAt) {
        this.lastActivateAt = lastActivateAt;
    }

    public String getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", role=" + role +
                ", createAt=" + createAt +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", lastActivateAt=" + lastActivateAt +
                ", oauthProvider='" + oauthProvider + '\'' +
                ", oauthId='" + oauthId + '\'' +
                '}';
    }
}
