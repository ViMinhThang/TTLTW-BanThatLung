package com.thomas.services;

import com.thomas.dao.UsersUsageDao;
import com.thomas.dao.model.UsersUsage;

import java.time.LocalDateTime;
import java.util.List;

public class UsesUsageService {
    UsersUsageDao usersUsageDao;

    public UsesUsageService() {
        usersUsageDao = new UsersUsageDao();
    }

    public List<UsersUsage> getAllUsersUsage() {
        return usersUsageDao.getAllUsersUsage();
    }

    public void trackUserActivity(String email, String ipAddress) {
        usersUsageDao.insertUsersUsage(email, ipAddress);
    }

    public void trackUserActivityNotLogin(String email, String route) {
        usersUsageDao.insertUsersUsageNotLogin(email, route);
    }
}
