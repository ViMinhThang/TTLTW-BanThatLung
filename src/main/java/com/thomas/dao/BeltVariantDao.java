package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.BeltVariant;
import org.jdbi.v3.core.statement.Query;

import java.time.LocalDateTime;
import java.util.List;

public class BeltVariantDao implements UsageInterface {


    public boolean createVariant(BeltVariant beltVariant) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "insert into beltvariants (beltId,colorId,sizeId,price,createdAt,updatedAt) values (:beltId,:colorId,:sizeId,:price,:createdAt,:updatedAt)";
            return h.createUpdate(sql).bind("beltId", beltVariant.getBeltId()).bind("colorId", beltVariant.getColorId()).bind("sizeId", beltVariant.getSizeId()).bind("createdAt", beltVariant.getCreatedAt()).bind("updatedAt", beltVariant.getUpdatedAt()).bind("price", beltVariant.getPrice()).execute() > 0;
        });
    }

    public boolean createVariantAndLog(BeltVariant beltVariant, int userId) {
        boolean result = createVariant(beltVariant);

        if (result) {
            saveLogToDB(userId, "Tạo biến thể", "xanh");
            return true;
        } else {
            return false;
        }
    }


    public List<BeltVariant> findVariants(int beltId, Integer color, Integer size, Integer variantId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM beltVariants WHERE beltId = :beltId");

        if (color != null) {
            sql.append(" AND colorId = :color");
        }
        if (size != null) {
            sql.append(" AND sizeId = :size");
        }
        if (variantId != null) {
            sql.append(" AND id = :variantId");
        }

        return JDBIConnect.get().withHandle(handle -> {
            Query query = handle.createQuery(sql.toString()).bind("beltId", beltId);

            if (color != null) {
                query.bind("colorId", color);
            }
            if (size != null) {
                query.bind("sizeId", size);
            }
            if (variantId != null) {
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

    public boolean deleteVariantAndLog(Integer beltId, Integer variantId, int userId) {
        boolean result = deleteVariant(beltId, variantId);
        if (result) {
            saveLogToDB(userId, "Xóa bản thể số " + variantId, "Đỏ");
            return true;
        } else {
            return false;
        }
    }

    public boolean saveVariants(BeltVariant beltVariant) {
        String sql = "UPDATE beltVariants SET colorId = :colorId,price= :price, sizeId = :sizeId,createdAt = :createdAt, updatedAt = :updatedAt WHERE id = :variantId AND beltId = :beltId";
        return JDBIConnect.get().withHandle(h -> {
            return h.createUpdate(sql).bind("variantId", beltVariant.getId()).bind("colorId", beltVariant.getColorId()).bind("sizeId", beltVariant.getSizeId()).bind("createdAt", beltVariant.getCreatedAt()).bind("updatedAt", beltVariant.getUpdatedAt()).bind("beltId", beltVariant.getBeltId()).bind("price", beltVariant.getPrice()).execute() > 0;
        });
    }

    public void saveLogToDB(int userId, String log, String alert) {
        JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO usersusage (userId,lastActivity,label,alert) VALUES(:userId,:lastActivity,:label,:alert)";
            return h.createUpdate(sql).bind("userId", userId).bind("lastActivity", LocalDateTime.now()).bind("alert", alert).bind("label", log).execute() > 0;
        });
    }

    public int findColorByName(String color) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id FROM colors WHERE name = :color";
            return h.createQuery(sql).bind("color", color).mapTo(Integer.class).findFirst().orElse(null);
        });
    }

    public int findSizeByName(String size) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id FROM sizes WHERE name = :size";
            return h.createQuery(sql).bind("size", size).mapTo(Integer.class).findFirst().orElse(null);
        });
    }

    public String findColorNameById(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT name FROM colors c JOIN beltVariants bv ON bv.colorId=c.id WHERE bv.id = :id";
            return h.createQuery(sql).bind("id", id).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public String findSizeNameById(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT name FROM sizes s JOIN beltVariants bv ON bv.sizeId=s.id WHERE bv.id = :id";
            return h.createQuery(sql).bind("id", id).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public List<BeltVariant> similarVariants(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM beltVariants WHERE beltId=:id";
            return h.createQuery(sql).bind("id", id).mapTo(BeltVariant.class).list();
        });
    }
}
