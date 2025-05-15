package com.thomas.services;

import com.thomas.dao.UserDao;
import com.thomas.dao.UsersUsageDao;
import com.thomas.dao.model.UsersUsage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UsesUsageService {
    UsersUsageDao usersUsageDao;
    UserDao userDao;

    public UsesUsageService() {
        usersUsageDao = new UsersUsageDao();
        userDao = new UserDao();
    }

    public List<UsersUsage> getAllUsersUsage() {
        return usersUsageDao.getAllUsersUsage().stream().map(v -> {
                    String email = userDao.findUserEmail(null, v.getUserId()).getEmail();
                    v.setUserEmail(email);
                    return v;
                })
                .collect(Collectors.toList());
    }

    public void trackUserActivity(int userId, String ipAddress) {
        usersUsageDao.insertUsersUsage(userId, ipAddress);
    }

    public void trackUserActivityNotLogin(int userId, String route) {
        usersUsageDao.insertUsersUsageNotLogin(userId, route);
    }
}
