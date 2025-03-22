package com.thomas.dao.db;

import com.thomas.dao.model.Reviews;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Reviews> {
    public Reviews map(ResultSet rs, StatementContext ctx) throws SQLException {
        Reviews review = new Reviews();
        review.setId(rs.getInt("id"));
        review.setBeltId(rs.getInt("beltId"));
        review.setUserId(rs.getInt("userId"));
        review.setContent(rs.getString("content"));
        review.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        review.setReviewerStar(rs.getInt("ratingStar"));
        return review;
    }
}
