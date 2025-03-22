package com.thomas.dao.db;

import com.thomas.dao.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setImage(rs.getString("image"));
        user.setCreateAt(rs.getTimestamp("createdAt").toLocalDateTime());
        user.setIsDeleted(rs.getInt("isDeleted"));
        user.setGender(rs.getString("gender"));
        user.setPhoneNumber(rs.getLong("phoneNumber"));
        user.setRole(rs.getInt("role"));
        user.setIsActive(rs.getInt("isActive"));
        user.setLastActivateAt(rs.getTimestamp("lastActivateAt").toLocalDateTime());
        return user;
    }
}
