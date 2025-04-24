package com.thomas.dao.db;

import com.thomas.dao.model.Transactions;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper {
    public Transactions map(ResultSet rs, StatementContext ctx) throws SQLException {
        Transactions trans = new Transactions();
        trans.setId(rs.getInt("id"));
        trans.setBeltVariantId(rs.getInt("beltVariantId"));
        trans.setBeltId(rs.getInt("beltId"));
        trans.setTransactionDate(rs.getTimestamp("transactionDate").toLocalDateTime());
        trans.setQuantity(rs.getInt("quantity"));
        trans.setTransactionType(rs.getString("transactionType"));
        trans.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        trans.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        return trans;
    }
}
