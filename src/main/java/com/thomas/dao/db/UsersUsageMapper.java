package com.thomas.dao.db;

import com.thomas.dao.model.UsersUsage;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersUsageMapper implements RowMapper<UsersUsage> {
    public UsersUsage map(ResultSet rs, StatementContext ctx) throws SQLException {
        UsersUsage usersUsage = new UsersUsage();
        usersUsage.setId(rs.getInt("id"));
        usersUsage.setUserEmail(rs.getString("userEmail"));
        usersUsage.setLastLogin(rs.getTimestamp("lastLogin").toLocalDateTime());
        usersUsage.setLastActivity(rs.getTimestamp("lastActivity").toLocalDateTime());
        usersUsage.setIpAddress(rs.getString("ipAddress"));
        usersUsage.setLastActivateRoute(rs.getString("lastActivateRoute"));
        return usersUsage;
    }
}
