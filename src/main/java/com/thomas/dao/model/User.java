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

    public User() {

    }

    public User(String password, String name, String email, long phoneNumber, LocalDate dateOfBirth, String gender, String image, int role, LocalDateTime createAt, int isDeleted, int isActive, LocalDateTime lastActivateAt) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.image = image;
        this.role = role;
        this.createAt = createAt;
        this.lastActivateAt = lastActivateAt;
    }

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

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public String getFirstName() {
        return this.name.substring(this.name.lastIndexOf(" ") + 1);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phoneNumber +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", role=" + role +
                ", createAt='" + createAt + '\'' +
                '}';
    }

    public LocalDateTime getLastActivateAt() {
        return lastActivateAt;
    }

    public void setLastActivateAt(LocalDateTime lastActivateAt) {
        this.lastActivateAt = lastActivateAt;
    }

}
