package com.thomas.dao.db;

import com.thomas.dao.model.Belts;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeltMapper implements RowMapper<Belts> {
    @Override
    public Belts map(ResultSet rs, StatementContext ctx) throws SQLException {
        Belts belts = new Belts();
        belts.setId(rs.getInt("id"));
        belts.setName(rs.getString("name"));
        belts.setDescription(rs.getString("description"));
        belts.setPrice(rs.getDouble("price"));
        belts.setGender(rs.getString("gender"));
        belts.setReleaseDate(rs.getTimestamp("releaseDate").toLocalDateTime());
        belts.setReleaseDate(rs.getTimestamp("createdDate").toLocalDateTime());
        belts.setReleaseDate(rs.getTimestamp("updatedDate").toLocalDateTime());
        belts.setIsDeleted(rs.getInt("isDeleted"));
        belts.setDiscountRate(rs.getDouble("discountRate"));
        belts.setMaterialBelt(rs.getString("materialBelt"));
        return belts;
    }
}
