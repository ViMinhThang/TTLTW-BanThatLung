package com.thomas.dao.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class UsersUsage implements java.io.Serializable {
    private int id;
    private String userEmail;
    private LocalDateTime lastLogin;
    private LocalDateTime lastActivity;
    private String ipAddress;
    private String lastActivateRoute;

    public UsersUsage() {

    }

    public UsersUsage(int id, String userEmail, LocalDateTime lastLogin, LocalDateTime lastActivity, String ipAddress, String lastActivateRoute) {
        this.id = id;
        this.userEmail = userEmail;
        this.lastLogin = lastLogin;
        this.lastActivity = lastActivity;
        this.ipAddress = ipAddress;
        this.lastActivateRoute = lastActivateRoute;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getLastActivity() {
        return this.lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLastActivateRoute() {
        return this.lastActivateRoute;
    }

    public void setLastActivateRoute(String lastActivateRoute) {
        this.lastActivateRoute = lastActivateRoute;
    }

    private String formatTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) return "Không có dữ liệu";

        Duration duration = Duration.between(dateTime, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (minutes < 1) return "Vừa xong";
        if (minutes < 60) return minutes + " phút trước";
        if (hours < 24) return hours + " giờ trước";
        return days + " ngày trước";
    }

    public String getLastLoginFormatted() {
        return formatTimeAgo(lastLogin);
    }

    public String getLastActivityFormatted() {
        return formatTimeAgo(lastActivity);
    }
}
