package com.thomas.services;

import com.thomas.dao.UserDao;
import com.thomas.dao.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SignUpService {

    public boolean signUp(String email, String password, String name, String middleName, LocalDate birthDate) {
        UserDao userDao = new UserDao();
        User u = new User();
        if (userDao.findUserEmail(email) != null) {
            return false;
        }
        u.setEmail(email);
        u.setPassword(MD5Service.hashPassword(password));
        u.setName(middleName + " " + name);
        u.setIsDeleted(0);
        u.setDateOfBirth(birthDate);
        u.setCreateAt(LocalDateTime.now());
        u.setIsActive(0);
        return userDao.registerUser(u);
    }
}
