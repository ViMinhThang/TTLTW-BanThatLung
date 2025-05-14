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
            sql.append(" AND colorId = :colorId");
        }
        if (size != null) {
            sql.append(" AND sizeId = :sizeId");
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

    public List<String> getAllColors() {
        String sql = "SELECT name FROM colors";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).mapTo(String.class).list();
        });
    }

    public List<String> getAllSizesRelatedToBeltName(String beltName) {
        String sql = "SELECT DISTINCT s.name FROM sizes s JOIN beltVariants v ON v.sizeId=s.id " +
                "JOIN belts b ON b.id = v.beltId " +
                "WHERE b.name=:beltName";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("beltName", beltName).mapTo(String.class).list();
        });
    }

    public List<String> getAllColorRelatedToBeltName(String beltName) {
        String sql = "SELECT DISTINCT c.name FROM colors c JOIN beltVariants v ON v.colorId=c.id " +
                "JOIN belts b ON b.id = v.beltId " +
                "WHERE b.name=:beltName";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("beltName", beltName).mapTo(String.class).list();
        });
    }

    public List<String> getAllcolorsByNameBeltAndSize(String beltName, String size) {
        String sql = "SELECT DISTINCT c.name FROM colors c JOIN beltVariants v ON v.colorId=c.id " +
                "JOIN belts b ON b.id = v.beltId " +
                "JOIN sizes s ON v.sizeId=s.id " +
                "WHERE b.name=:beltName AND s.name=:size";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("beltName", beltName).bind("size", size).mapTo(String.class).list();
        });
    }

    public List<String> getAllSizeByNameBeltAndColor(String beltName, String color) {
        String sql = "SELECT DISTINCT s.name FROM sizes s JOIN beltVariants v ON v.sizeId=s.id " +
                "JOIN belts b ON b.id = v.beltId " +
                "JOIN colors c ON v.colorId=c.id " +
                "WHERE b.name=:beltName AND c.name=:color";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("beltName", beltName).bind("color", color).mapTo(String.class).list();
        });
    }

    public List<String> getBeltNameAlike(String keyword) {
        String sql = "SELECT b.name FROM belts b WHERE b.name LIKE :keyword AND b.isDeleted = 0 LIMIT 10";
        return JDBIConnect.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("keyword", "%" + keyword + "%")
                        .mapTo(String.class)
                        .list()
        );
    }

    public int getQuantity(String name, String color, String size) {
        String sql = "SELECT i.stockQuantity " +
                "FROM inventory i " +
                "JOIN beltVariants v ON v.id = i.variantId " +
                "JOIN colors c ON v.colorId = c.id " +
                "JOIN sizes s ON v.sizeId = s.id " +
                "JOIN belts b ON b.id = v.beltId " +
                "WHERE b.name = :name AND c.name = :color AND s.name = :size";

        return JDBIConnect.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("name", name)
                        .bind("color", color)
                        .bind("size", size)
                        .mapTo(Integer.class)
                        .findFirst()
                        .orElse(0)
        );
    }

}
