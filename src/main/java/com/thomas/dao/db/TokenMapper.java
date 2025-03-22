package com.thomas.dao.db;

import com.thomas.dao.model.Token;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<Token> {
    public Token map(ResultSet rs, StatementContext ctx) throws SQLException {
        Token token = new Token();
        token.setTokenId(rs.getString("tokenId"));
        token.setUserId(rs.getInt("userId"));
        token.setExpiryDate(rs.getTimestamp("expiresAt").toLocalDateTime());
        return token;
    }
}
