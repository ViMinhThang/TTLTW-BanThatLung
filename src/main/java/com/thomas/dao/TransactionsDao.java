package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Transactions;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionsDao {
    public List<Transactions> getTransactions(Integer id) {
        String sql = "select * from stockTransactions";
        if (id != null) {
            sql += " where id = " + id;
        }
        final String finalSql = sql;
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(finalSql).mapToBean(Transactions.class).list();
        });
    }

    public boolean updateTransactions(Transactions transactions) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE stockTransactions SET beltId=:beltId,beltVariantId=:beltVariantId,transactionType=:transactionType,quantity=:quantity,transactionDate=:transactionDate,createdAt=:createdAt,updatedAt=:updatedAt";
            return h.createUpdate(sql).bind("beltId", transactions.getBeltId())
                    .bind("beltVariantId", transactions.getBeltVariantId())
                    .bind("transactionType", transactions.getTransactionType())
                    .bind("quantity", transactions.getQuantity())
                    .bind("transactionDate", transactions.getTransactionDate())
                    .bind("createdAt", transactions.getCreatedAt())
                    .bind("updatedAt", transactions.getUpdatedAt()).execute() > 0;
        });
    }

    public boolean deleteTransactions(Transactions transactions) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "delete from stockTransactions where id =:id";
            return h.createUpdate(sql).bind("id", transactions.getId()).execute() > 0;
        });
    }

    public boolean addTransactions(Transactions transactions) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO stockTransactions(beltId,beltVariantId,transactionType,quantity,transactionDate,createdAt,updatedAt) VALUES(:beltId,beltVariantId,transactionType,:transactionType,quantity,:transactionDate,:createdAt,:updatedAt) ";
            return h.createUpdate(sql).bind("beltId", transactions.getBeltId())
                    .bind("beltVariantId", transactions.getBeltVariantId())
                    .bind("transactionType", transactions.getTransactionType())
                    .bind("quantity", transactions.getQuantity())
                    .bind("transactionDate", transactions.getTransactionDate())
                    .bind("createdAt", transactions.getCreatedAt())
                    .bind("updatedAt", transactions.getUpdatedAt()).execute() > 0;
        });
    }

    public String findBeltName(int beltVariantId, String transactionType, LocalDateTime transactionDate, int beltId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT CONCAT(b.name, ' ', v.color, ' ', v.size) " +
                    "FROM belts b " +
                    "JOIN beltVariants v ON v.beltId = b.id " +
                    "JOIN stockTransactions p ON p.beltVariantId = v.id " +
                    "WHERE p.beltVariantId = :beltVariantId " +
                    "AND p.transactionType = :transactionType " +
                    "AND p.transactionDate = :transactionDate " +
                    "AND p.beltId = :beltId";

            return h.createQuery(sql)
                    .bind("beltVariantId", beltVariantId)
                    .bind("transactionDate", Timestamp.valueOf(transactionDate))
                    .bind("transactionType", transactionType)
                    .bind("beltId", beltId).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public int findBeltId(String beltName) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id from belts WHERE name=:beltName";
            return h.createQuery(sql).bind("beltName", beltName).mapTo(Integer.class).findFirst().orElse(null);
        });
    }
    public int findVariantId(String beltName, String color, String size) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT v.id FROM beltVariants v " +
                    "JOIN belts b ON b.id =v.beltId " +
                    "WHERE color=:color AND size=:size AND b.name = :beltName";
            return h.createQuery(sql).bind("color", color)
                    .bind("beltName", beltName)
                    .bind("size", size).mapTo(Integer.class).findFirst().orElse(null);
        });
    }
}
