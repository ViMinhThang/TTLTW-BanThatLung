package com.thomas.dao.db;

import com.thomas.dao.model.CartItem;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements RowMapper<CartItem> {
    public CartItem map(ResultSet rs, StatementContext ctx) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.setId(rs.getInt("id"));
        cartItem.setVariantId(rs.getInt("variantId"));
        cartItem.setQuantity(rs.getInt("quantity"));
        cartItem.setPrice(rs.getDouble("price"));
        cartItem.setBeltId(rs.getInt("beltId"));
        cartItem.setUserId(rs.getInt("userId"));
        cartItem.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        cartItem.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return cartItem;
    }
}
