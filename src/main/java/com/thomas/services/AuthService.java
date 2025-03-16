package com.thomas.services;

import com.thomas.dao.UserDao;
import com.thomas.dao.model.User;

public class AuthService {
    UserDao udao = new UserDao();

    public User checkLogin(String email, String password) {
        User u = udao.findUserEmail(email);
        if (u != null && password != null && u.getIsActive() == 1 && u.getIsDeleted() == 0) {

            if (MD5Service.hashPassword(password).equals(u.getPassword())) {
                u.setPassword(null);
                return u;
            }
        }

        return null;
    }

    public User getUserBySessionID(String sessionID) {
        return udao.getUserBySessionId(sessionID);
    }

    public boolean saveSession(int userId, String sessionID) {
        return udao.saveSession(userId, sessionID);
    }

    public boolean updateSessionId(int userId, String sessionID) {
        return udao.updateSessionId(userId, sessionID);
    }

    public boolean deleteSession(int userId) {
        return udao.deleteSession(userId);
    }
}
