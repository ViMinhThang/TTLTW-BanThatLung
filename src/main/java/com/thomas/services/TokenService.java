package com.thomas.services;

import com.thomas.dao.TokenDao;
import com.thomas.dao.UserDao;
import com.thomas.dao.model.Token;
import com.thomas.dao.model.User;

public class TokenService {
    UserDao userDao;
    TokenDao tokenDao;

    public TokenService() {
        this.userDao = new UserDao();
        this.tokenDao = new TokenDao();
    }

    public boolean updatePasswordByToken(String token, String password) {
        int userId = tokenDao.verifyToken(token);
        User u = userDao.findUserById(userId);
        u.setPassword(password);
        return userDao.updateUserPassword(u);
    }

    public int verifyToken(String token) {
        return tokenDao.verifyToken(token);
    }

    public boolean saveResetToken(Token token) {
        boolean deleteToken = tokenDao.deleteToken(token.getUserId());
        return tokenDao.saveToken(token);
    }

    public boolean activeUser(int userId) {
        return userDao.activeByToken(userId);
    }

}
