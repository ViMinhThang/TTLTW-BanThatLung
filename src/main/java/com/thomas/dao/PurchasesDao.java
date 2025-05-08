package com.thomas.dao;

import com.thomas.dao.db.InventoryMapper;
import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Inventory;
import com.thomas.dao.model.Purchases;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class PurchasesDao {
    public List<Purchases> getPurchases(Integer purchaseId) {
        String sql = "select * from purchases ";
        if (purchaseId != null) {
            sql += "where id =" + purchaseId;
        }
        final String finalSql = sql;
        return JDBIConnect.get().withHandle(h -> h.createQuery(finalSql).mapToBean(Purchases.class).list());
    }

    public boolean updatePurchases(Purchases purchases) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE purchases SET supplierId=:supplierId,beltVariantId=:beltVariantId,quantity=:quantity,purchaseDate=:purchaseDate,createdAt=:createdAt,updatedAt=:updatedAt WHERE id =:purchaseId";
            return h.createUpdate(sql).bind("supplierId", purchases.getSupplierId())
                    .bind("beltVariantId", purchases.getBeltVariantId())
                    .bind("purchaseId", purchases.getId())
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
            String sql = "SELECT CONCAT(b.name, ' ', c.name, ' ', s.name) " +
                    "FROM belts b " +
                    "JOIN beltVariants v ON v.beltId = b.id " +
                    "JOIN colors c ON c.Id = v.colorId " +
                    "JOIN sizes s ON s.Id = v.sizeId " +
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

    public int findVariantId(String beltName, int color, int size) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT v.id FROM beltVariants v " +
                    "JOIN belts b ON b.id =v.beltId " +
                    "WHERE v.colorId=:color AND v.sizeId=:size AND b.name = :beltName";
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

    public boolean addInventory(Inventory inventory) {
        String sql = "INSERT INTO inventory(beltId,variantId,stockQuantity) VALUES(:beltId, :variantId, :stockQuantity)";
        return JDBIConnect.get().withHandle(h -> {
            return h.createUpdate(sql).bind("beltId", inventory.getBeltId())
                    .bind("variantId", inventory.getVariantId())
                    .bind("beltId", inventory.getBeltId())
                    .bind("stockQuantity", inventory.getStockQuantity()).execute() > 0;
        });
    }

    public Inventory checkInventory(int beltId, int variantId) {
        String sql = "SELECT * from inventory where beltId=:beltId AND variantId=:variantId";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("beltId", beltId).bind("variantId", variantId).mapTo(Inventory.class).findFirst().orElse(null);
        });
    }

    public List<Inventory> getInventory(Integer beltId, Integer variantId) {
        String sql = "SELECT * FROM inventory";
        boolean hasBelt = beltId != null;
        boolean hasVariant = variantId != null;

        // Dynamically build WHERE clause based on parameters
        if (hasBelt && hasVariant) {
            sql += " WHERE beltId = :beltId AND variantId = :variantId";
        } else if (hasBelt) {
            sql += " WHERE beltId = :beltId";
        } else if (hasVariant) {
            sql += " WHERE variantId = :variantId";
        }

        String finalSql = sql;
        return JDBIConnect.get().withHandle(handle -> {
            var query = handle.createQuery(finalSql);

            if (hasBelt) {
                query.bind("beltId", beltId);
            }
            if (hasVariant) {
                query.bind("variantId", variantId);
            }

            return query.map(new InventoryMapper()).list();
        });
    }


    public String findInventoryBelt(int beltId, int variantId) {
        String sql = "SELECT b.name FROM belts b JOIN inventory i on b.id = i.beltId WHERE i.beltId=:beltId";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("beltId", beltId).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public int[] findInventoryVariant(int beltId, int variantId) {
        String sql = "SELECT sizeId, colorId FROM beltVariants WHERE id = :variantId AND beltId=:beltId";
        return JDBIConnect.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("variantId", variantId)
                        .bind("beltId", beltId)
                        .map((rs, ctx) -> new int[]{rs.getInt("sizeId"), rs.getInt("colorId")})
                        .findFirst()
                        .orElse(null)
        );
    }

    public String getColorName(int colorId) {
        String sql = "SELECT name FROM colors WHERE id=:colorId";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("colorId", colorId).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public String getSizeName(int sizeId) {
        String sql = "SELECT name FROM sizes WHERE id=:sizeId";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("sizeId", sizeId).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public boolean updateInventory(Inventory inventory) {
        String sql = "UPDATE inventory SET stockquantity=:stockQuantity WHERE id=:id";
        return JDBIConnect.get().withHandle(h -> {
            return h.createUpdate(sql).bind("id", inventory.getId()).bind("stockQuantity", inventory.getStockQuantity()).execute() > 0;
        });
    }
}
