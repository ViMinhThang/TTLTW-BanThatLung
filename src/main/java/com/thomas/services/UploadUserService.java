package com.thomas.services;

import com.thomas.dao.UserDao;
import com.thomas.dao.model.User;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UploadUserService {
    UserDao userDao;

    public UploadUserService() {
        this.userDao = new UserDao();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUser(int userId) {
        return userDao.findUserById(userId);
    }

    public boolean deleteUser(int userId, int editorId) {
        return userDao.deleteUserAndLog(userId, editorId);
    }

    public boolean softDeleteUser(int userId) {
        return userDao.softDeleteUser(userId);
    }

    public boolean updateUser(int userId, String userName, String email, String gender, int role, LocalDate birthDate, long phone, int isDeleted, int editorId) {
        User user = new User();
        user.setId(userId);
        user.setName(userName);
        user.setEmail(email);
        user.setGender(gender);
        user.setRole(role);
        user.setPhoneNumber(phone);
        user.setIsDeleted(isDeleted);
        user.setDateOfBirth(birthDate);
        return userDao.updateUserAndLog(user, editorId);
    }

    public boolean saveUser(String userName, String email, String password, String gender, int role, LocalDate birthDate, long phone, int isDeleted) {
        User user = new User();
        if (userDao.findUserEmail(email, null) != null) {
            return false;
        }
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setRole(role);
        user.setPhoneNumber(phone);
        user.setIsDeleted(isDeleted);
        user.setDateOfBirth(birthDate);
        user.setCreateAt(LocalDateTime.now());
        return userDao.registerUser(user);
    }

    public boolean updateUserPassword(String password, int userId) {
        User user = userDao.findUserById(userId);
        user.setPassword(password);
        return userDao.updateUserPassword(user);
    }

    public boolean updateEmail(HttpServletRequest request, String email, int userId, String password, int editorId) {
        User user = userDao.findUserById(userId);
        if (MD5Service.hashPassword(password).equals(user.getPassword())) {
            user.setEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("auth", user);
            return userDao.updateUserAndLog(user, editorId);

        } else {
            return false;
        }
    }

    public void updateImagePath(HttpServletRequest request, int userId, String mainImage) {
        User user = userDao.findUserById(userId);
        user.setImage(mainImage);
        HttpSession session = request.getSession();
        session.setAttribute("auth", user);
        userDao.updateUserImage(user);
    }

    public void updateUserInfo(int userId, String gender, LocalDate birthday, long phoneNumber, int editorId) {
        User user = userDao.findUserById(userId);
        user.setGender(gender);
        user.setDateOfBirth(birthday);
        user.setPhoneNumber(phoneNumber);
        userDao.updateUserAndLog(user, editorId);
    }

    public List<User> searchUser(String query) {
        return userDao.searchUser(query);
    }

    public User findUserByEmail(String email) {
        return userDao.findUserEmail(email, null);
    }

}
