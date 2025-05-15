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
        return allUsers;
    }

    public boolean insertUsersUsage(int userId, String ipAddress) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO usersUsage (userId, lastLogin, lastActivity, ipAddress) " +
                    "VALUES (:userId, NOW(), NOW(), :ipAddress) " +
                    "ON DUPLICATE KEY UPDATE lastLogin = NOW(), ipAddress = :ipAddress";

            return h.createUpdate(sql)
                    .bind("userId", userId)
                    .bind("ipAddress", ipAddress)
                    .execute() > 0;
        });
    }

    public boolean insertUsersUsageNotLogin(int userId, String route) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE usersUsage SET lastActivity = NOW(), lastActiveRoute=:route WHERE userId =:userId";
            return h.createUpdate(sql)
                    .bind("userId", userId)
                    .bind("route", route)
                    .execute() > 0;
        });
    }

}
