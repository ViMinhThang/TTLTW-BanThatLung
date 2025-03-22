package com.thomas.dao.db;

import com.thomas.dao.model.BeltVariant;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeltVariantsMapper implements RowMapper<BeltVariant> {
    @Override
    public BeltVariant map(ResultSet rs, StatementContext ctx) throws SQLException {
        BeltVariant beltVariant = new BeltVariant();
        beltVariant.setId(rs.getInt("id"));
        beltVariant.setBeltId(rs.getInt("beltId"));
        beltVariant.setColor(rs.getString("color"));
        beltVariant.setSize(rs.getString("size"));
        beltVariant.setStockQuantity(rs.getInt("stockQuantity"));
        beltVariant.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        beltVariant.setCreatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        return beltVariant;
    }
}
