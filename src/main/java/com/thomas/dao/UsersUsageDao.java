package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.UsersUsage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersUsageDao {
    public UsersUsageDao() {

    }

    public List<UsersUsage> getAllUsersUsage() {
        List<UsersUsage> allUsers = JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM usersUsage";
            return h.createQuery(sql).mapToBean(UsersUsage.class).list();
        });

        Map<String, UsersUsage> uniqueUsers = new HashMap<>();
        for (UsersUsage user : allUsers) {
            uniqueUsers.putIfAbsent(user.getUserEmail(), user);
            if (uniqueUsers.get(user.getUserEmail()).getLastLogin().isBefore(user.getLastLogin())) {
                uniqueUsers.put(user.getUserEmail(), user);
            }
        }

        return new ArrayList<>(uniqueUsers.values());
    }

    public boolean insertUsersUsage(String userEmail, String ipAddress) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO usersUsage (userEmail, lastLogin, lastActivity, ipAddress) " +
                    "VALUES (:userEmail, NOW(), NOW(), :ipAddress) " +
                    "ON DUPLICATE KEY UPDATE lastLogin = NOW(), ipAddress = :ipAddress";

            return h.createUpdate(sql)
                    .bind("userEmail", userEmail)
                    .bind("ipAddress", ipAddress)
                    .execute() > 0;
        });
    }

    public boolean insertUsersUsageNotLogin(String userEmail, String route) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE usersUsage SET lastActivity = NOW(), lastActivateRoute=:route WHERE userEmail =:email";
            return h.createUpdate(sql)
                    .bind("email", userEmail)
                    .bind("route", route)
                    .execute() > 0;
        });
    }

}
