package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public UserDao() {

    }

    public boolean saveResetToken(String toEmail, String token) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE users SET token = :token WHERE email = :email";
            return h.createUpdate(sql)
                    .bind("email", toEmail)
                    .bind("token", token)
                    .execute() > 0;
        });
    }

    public boolean deleteUserById(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM users WHERE id = :userId";
            return h.createUpdate(sql).bind("userId", userId).execute() > 0;
        });
    }

    public boolean updateUser(User user) {
        return JDBIConnect.get().withHandle(handle -> {
            // SQL query for updating the user
            String sql = "UPDATE users SET name = :name, email = :email, gender = :gender, dateOfBirth = :dateOfBirth, phoneNumber = :phone, role = :role, isDeleted = :isDeleted WHERE id = :userId";

            // Execute the update query with the user's data
            return handle.createUpdate(sql)
                    .bind("name", user.getName())
                    .bind("email", user.getEmail())
                    .bind("gender", user.getGender())
                    .bind("dateOfBirth", user.getDateOfBirth())
                    .bind("phone", user.getPhoneNumber())
                    .bind("role", user.getRole())
                    .bind("isDeleted", user.getIsDeleted())
                    .bind("userId", user.getId())
                    .execute() > 0;
        });
    }

    public boolean updateUserPassword(User user) {
        return JDBIConnect.get().withHandle(handle -> {
            // SQL query for updating the user
            String sql = "UPDATE users SET password = :password WHERE id = :userId";

            // Execute the update query with the user's data
            return handle.createUpdate(sql)
                    .bind("password", user.getPassword())
                    .bind("userId", user.getId())
                    .execute() > 0;
        });
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id DESC";

        // Using JDBI to fetch data
        JDBIConnect.get().withHandle(handle -> {
            try (ResultSet rs = handle.getConnection().createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
                    user.setPassword(rs.getString("password"));
                    user.setImage(rs.getString("image"));
                    user.setCreateAt(rs.getDate("createAt").toLocalDate());
                    user.setIsDeleted(rs.getInt("isDeleted"));
                    user.setGender(rs.getString("gender"));
                    user.setPhoneNumber(rs.getLong("phoneNumber"));
                    user.setRole(rs.getInt("role"));
                    userList.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        return userList;
    }


    public boolean verifyToken(String token) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM users WHERE token = :token";
            return h.createQuery(sql).bind("token", token).mapToMap().findOne().isPresent();
        });
    }

    public boolean setPasswordByToken(String token, String password) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE users SET password = :password WHERE token = :token";
            return h.createUpdate(sql)
                    .bind("password", password)
                    .bind("token", token)
                    .execute() > 0;
        });
    }


    public User findUserName(String username) {
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery("select * from users where name = :username AND isDeleted = 0").bind("username", username).mapToBean(User.class).findFirst().orElse(null);
        });
    }

    public User findUserEmail(String email) {
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery("select * from users where email = :email").bind("email", email).mapToBean(User.class).findFirst().orElse(null);
        });
    }

    public boolean registerUser(User user) {
        return JDBIConnect.get().withHandle(h -> {
            String insertedsql = "INSERT INTO users (name, email, dateOfBirth, password, createAt,gender,phoneNumber, isDeleted, role,isActive) " +
                    "VALUES (:name, :email, :dateOfBirth, :password, :createAt,:gender,:phoneNumber, :isDeleted, :role,:isActive)";
            return h.createUpdate(insertedsql)
                    .bind("name", user.getName())
                    .bind("email", user.getEmail())
                    .bind("dateOfBirth", Timestamp.valueOf(user.getDateOfBirth().atStartOfDay()))
                    .bind("password", user.getPassword())
                    .bind("createAt", Timestamp.valueOf(user.getCreateAt().atStartOfDay()))
                    .bind("isDeleted", user.getIsDeleted())
                    .bind("role", user.getRole())
                    .bind("gender", user.getGender())
                    .bind("phoneNumber", user.getPhoneNumber())
                    .bind("isActive", user.getIsActive())
                    .execute() > 0;
        });
    }

    public User findUserById(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery("select * from users where id = :userId")
                    .bind("userId", userId).mapToBean(User.class).findFirst().orElse(null);
        });
    }

    public boolean activeByToken(String token) {
        return JDBIConnect.get().withHandle(h -> {
            return h.createUpdate("update users set isActive = 1 where token = :token").bind("token", token).execute() > 0;
        });
    }

    public void updateUserImage(User user) {
        JDBIConnect.get().withHandle(h -> {
            String sql = "Update users SET image = :image WHERE id = :id";
            return h.createUpdate(sql).bind("image", user.getImage()).bind("id", user.getId()).execute() > 0;
        });
    }

    public boolean softDeleteUser(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE users SET isDeleted = 1 WHERE id = :id";
            return h.createUpdate(sql).bind("id", userId).execute() > 0;
        });
    }

    public List<User> searchUser(String query) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name " +
                "FROM users WHERE name LIKE ? AND isDeleted = 0";

        JDBIConnect.get().withHandle(handle -> {
            try (PreparedStatement ps = handle.getConnection().prepareStatement(sql)) {
                ps.setString(1, "%" + query + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        userList.add(user);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        return userList;
    }

    public User getUserBySessionId(String sessionId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "select * from users u JOIN sessions s on s.userId=u.id WHERE s.sessionId=:sessionId";
            return h.createQuery(sql).bind("sessionId", sessionId).mapToBean(User.class).findFirst().orElse(null);
        });
    }

    public boolean saveSession(int userId, String sessionId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO sessions (userId, sessionId) VALUES (:userId, :newSessionId) ON DUPLICATE KEY UPDATE sessionId = :newSessionId";
            return h.createUpdate(sql).bind("userId", userId).bind("newSessionId", sessionId).execute() > 0;
        });
    }

    public boolean updateSessionId(int userId, String newSessionId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO sessions (userId, sessionId) VALUES (:userId, :newSessionId) ON DUPLICATE KEY UPDATE sessionId = :newSessionId";
            return h.createUpdate(sql)
                    .bind("newSessionId", newSessionId)
                    .bind("userId", userId)
                    .execute() > 0;
        });
    }

    public boolean deleteSession(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM sessions WHERE userId = :userId";
            return h.createUpdate(sql)
                    .bind("userId", userId)
                    .execute() > 0;
        });
    }

}
