package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.BeltVariant;
import org.jdbi.v3.core.statement.Query;

import java.util.List;

public class BeltVariantDao {


    public boolean createVariant(BeltVariant beltVariant) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "insert into beltvariants (beltId,color,size,stockQuantity,createdAt,updatedAt) values (:beltId,:color,:size,:stockQuantity,:createdAt,:updatedAt)";
            return h.createUpdate(sql).bind("beltId", beltVariant.getBeltId())
                    .bind("color", beltVariant.getColor())
                    .bind("size", beltVariant.getSize())
                    .bind("stockQuantity", beltVariant.getStockQuantity())
                    .bind("createdAt", beltVariant.getCreatedAt())
                    .bind("updatedAt", beltVariant.getUpdatedAt()).execute() > 0;
        });
    }

    public List<BeltVariant> findVariants(int beltId, String color, String size, Integer variantId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM beltVariants WHERE beltId = :beltId");

        if (color != null && !color.isEmpty()) {
            sql.append(" AND color = :color");
        }
        if (size != null && !size.isEmpty()) {
            sql.append(" AND size = :size");
        }
        if (variantId != null) {
            sql.append(" AND id = :variantId");
        }

        return JDBIConnect.get().withHandle(handle -> {
            Query query = handle.createQuery(sql.toString()).bind("beltId", beltId);

            if (color != null && !color.isEmpty()) {
                query.bind("color", color);
            }
            if (size != null && !size.isEmpty()) {
                query.bind("size", size);
            }
            if (variantId != null) { // Bind variantId nếu có
                query.bind("variantId", variantId);
            }

            return query.mapToBean(BeltVariant.class).list();
        });
    }

    public boolean deleteVariant(Integer beltId, Integer variantId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql;

            if (variantId != null) {
                sql = "DELETE FROM beltVariants WHERE id = :id AND beltId = :beltId";
                return h.createUpdate(sql).bind("id", variantId).bind("beltId", beltId).execute() > 0;
            } else {
                sql = "DELETE FROM belts WHERE id = :beltId";
                return h.createUpdate(sql).bind("beltId", beltId).execute() > 0;
            }
        });
    }


    public void saveVariants(BeltVariant beltVariant) {
        String sql = "UPDATE beltVariants SET color = :color, size = :size,stockQuantity = :stockQuantity,createdAt = :createdAt, updatedAt = :updatedAt WHERE id = :variantId";
        JDBIConnect.get().withHandle(h -> {
            return h.createUpdate(sql).bind("variantId", beltVariant.getId())
                    .bind("color", beltVariant.getColor())
                    .bind("size", beltVariant.getSize())
                    .bind("stockQuantity", beltVariant.getStockQuantity())
                    .bind("createdAt", beltVariant.getCreatedAt())
                    .bind("updatedAt", beltVariant.getUpdatedAt())
                    .execute();
        });
    }
}
