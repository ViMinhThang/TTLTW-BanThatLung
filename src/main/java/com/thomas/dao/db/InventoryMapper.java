package com.thomas.dao.db;

import com.thomas.dao.model.Inventory;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryMapper implements RowMapper<Inventory> {
    @Override
    public Inventory map(ResultSet rs, StatementContext ctx) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getInt("id"));
        inventory.setVariantId(rs.getInt("variantId"));
        inventory.setBeltId(rs.getInt("beltId"));
        inventory.setStockQuantity(rs.getInt("stockQuantity"));
        return inventory;
    }
}
