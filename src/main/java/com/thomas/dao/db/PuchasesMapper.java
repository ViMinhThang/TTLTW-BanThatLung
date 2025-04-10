package com.thomas.dao.db;

import com.thomas.dao.model.Purchases;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PuchasesMapper implements RowMapper<Purchases> {

    @Override
    public Purchases map(ResultSet rs, StatementContext ctx) throws SQLException {
        Purchases purchases = new Purchases();
        purchases.setId(rs.getInt("id"));
        purchases.setSupplierId(rs.getInt("supplierId"));
        purchases.setBeltVariantId(rs.getInt("beltVariantId"));
        purchases.setQuantity(rs.getInt("quantity"));
        purchases.setPurchaseDate(rs.getTimestamp("purchaseDate").toLocalDateTime());
        purchases.setCreatedAt(rs.getTimestamp("createAt").toLocalDateTime());
        purchases.setUpdatedAt(rs.getTimestamp("updateAt").toLocalDateTime());
        return purchases;
    }
}
