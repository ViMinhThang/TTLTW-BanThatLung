package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Purchases;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class PurchasesDao {
    public List<Purchases> getPurchases(Integer purchaseId) {
        String sql = "select * from purchases ";
        if (purchaseId != null) {
            sql += "where purchaseId =" + purchaseId;
        }
        final String finalSql = sql;
        return JDBIConnect.get().withHandle(h -> h.createQuery(finalSql).mapToBean(Purchases.class).list());
    }

    public boolean updatePurchases(Purchases purchases) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE purchases SET supplierId=:supplierId,beltVariantId=:beltVariantId,quantity=:quantity,purchaseDate=:purchaseDate,createdAt=:createdAt,updatedAt=:updatedAt ";
            return h.createUpdate(sql).bind("supplierId", purchases.getSupplierId())
                    .bind("beltVariantId", purchases.getBeltVariantId())
                    .bind("quantity", purchases.getQuantity())
                    .bind("purchaseDate", purchases.getPurchaseDate())
                    .bind("createdAt", purchases.getCreatedAt())
                    .bind("updatedAt", LocalDateTime.now()).execute() > 0;
        });
    }

    public boolean deletePurchases(Purchases purchases) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "delete from purchases where id =:purchaseId";
            return h.createUpdate(sql).bind("purchaseId", purchases.getId()).execute() > 0;
        });
    }

    public boolean createPurchases(Purchases purchases) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO purchases(supplierId, beltId, beltVariantId, quantity, purchaseDate, createdAt, updatedAt) " +
                    "VALUES(:supplierId, :beltId, :beltVariantId, :quantity, :purchaseDate, :createdAt, :updatedAt)";
            return h.createUpdate(sql).bind("supplierId", purchases.getSupplierId())
                    .bind("beltVariantId", purchases.getBeltVariantId())
                    .bind("quantity", purchases.getQuantity())
                    .bind("purchaseDate", purchases.getPurchaseDate())
                    .bind("createdAt", LocalDateTime.now())
                    .bind("beltId", purchases.getBeltId())
                    .bind("updatedAt", LocalDateTime.now()).execute() > 0;
        });
    }

    public String findSupplierName(int supplierId, int beltVariantId, LocalDateTime purchaseDate) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT name from suppliers s JOIN purchases p ON p.supplierId=s.id WHERE p.supplierId=:supplierId AND p.beltVariantId=:beltVariantId AND purchaseDate=:purchaseDate";
            return h.createQuery(sql).bind("supplierId", supplierId)
                    .bind("beltVariantId", beltVariantId)
                    .bind("purchaseDate", purchaseDate).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public String findBeltName(int supplierId, int beltVariantId, LocalDateTime purchaseDate, int beltId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT CONCAT(b.name, ' ', v.color, ' ', v.size) " +
                    "FROM belts b " +
                    "JOIN beltVariants v ON v.beltId = b.id " +
                    "JOIN purchases p ON p.beltVariantId = v.id " +
                    "WHERE p.supplierId = :supplierId " +
                    "AND p.beltVariantId = :beltVariantId " +
                    "AND p.purchaseDate = :purchaseDate " +
                    "AND p.beltId = :beltId";

            return h.createQuery(sql)
                    .bind("supplierId", supplierId)
                    .bind("beltVariantId", beltVariantId)
                    .bind("purchaseDate", Timestamp.valueOf(purchaseDate))
                    .bind("beltId", beltId).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public int findSupplierId(String name) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id from suppliers WHERE name=:name";
            return h.createQuery(sql).bind("name", name).mapTo(Integer.class).findFirst().orElse(null);
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

    public int findBeltId(String beltName) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id from belts WHERE name=:beltName";
            return h.createQuery(sql).bind("beltName", beltName).mapTo(Integer.class).findFirst().orElse(null);
        });
    }
}
