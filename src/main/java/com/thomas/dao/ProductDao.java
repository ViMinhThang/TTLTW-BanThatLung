package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Belts;

import org.jdbi.v3.core.statement.Query;


import java.time.LocalDateTime;
import java.util.List;

public class ProductDao {
    public boolean createProduct(Belts belts) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO belts ( name, releaseDate,createdAt, gender, price ,materialBelt,isDeleted,discountRate) VALUES (:productName,:releaseDate,:createdAt,:gender,:price,:material,:isDeleted,:discountRate)";
            return h.createUpdate(sql).bind("productName", belts.getName())
                    .bind("releaseDate", belts.getReleaseDate())
                    .bind("gender", belts.getGender())
                    .bind("price", belts.getPrice())
                    .bind("createdAt", belts.getCreatedAt())
                    .bind("material", belts.getMaterialBelt())
                    .bind("isDeleted", belts.getIsDeleted())
                    .bind("discountRate", belts.getDiscountRate())
                    .bind("createdAt", LocalDateTime.now())
                    .execute() > 0;
        });
    }

    public String[] getTags(int beltId) {
        return JDBIConnect.get().withHandle(handle -> {
            // Execute the query to fetch category names
            List<String> categories = handle.createQuery(
                            "SELECT c.name " +
                                    "FROM beltCategory bc " +
                                    "JOIN categories c ON bc.categoryId = c.id " +
                                    "WHERE bc.beltId = :beltId"
                    )
                    .bind("beltId", beltId)
                    .mapTo(String.class)
                    .list();

            // Convert the list to an array and return
            return categories.toArray(new String[0]);
        });
    }


    public List<String> getProductImages(int beltId) {
        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(
                                "SELECT imagePath FROM imageEntry WHERE beltId = :beltId AND imageType IN ('main', 'extra')")
                        .bind("beltId", beltId)
                        .mapTo(String.class)
                        .list()
        );
    }


    public void saveDesc(int beltId, String desc, int variantId) {
        JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE belts SET description = :desc WHERE id = :beltId AND variantId = :variantId";
            return h.createUpdate(sql)
                    .bind("variantId", variantId)
                    .bind("beltId", beltId)
                    .bind("desc", desc)
                    .execute() > 0;
        });
    }


    public List<Belts> find(Integer id) {
        String sql = (id != null)
                ? "SELECT * FROM belts WHERE id = :id ORDER BY id DESC"
                : "SELECT * FROM belts ORDER BY id DESC";

        return JDBIConnect.get().withHandle(handle -> {
            Query query = handle.createQuery(sql);
            if (id != null) {
                query.bind("id", id);
            }
            return query.mapToBean(Belts.class).list();
        });
    }

    public boolean updateProduct(Belts belts) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE belts SET name = :productName, " +
                    "releaseDate = :releaseDate, " +
                    "gender = :gender, " +
                    "price = :price, " +
                    "materialBelt = :material, " +
                    "isDeleted = :isDeleted, " +
                    "discountRate = :discountRate " +
                    "WHERE id = :id";

            return h.createUpdate(sql)
                    .bind("productName", belts.getName())
                    .bind("releaseDate", belts.getReleaseDate())
                    .bind("gender", belts.getGender())
                    .bind("price", belts.getPrice())
                    .bind("material", belts.getMaterialBelt())
                    .bind("id", belts.getId())
                    .bind("isDeleted", belts.getIsDeleted())
                    .bind("discountRate", belts.getDiscountRate())
                    .execute() > 0;
        });
    }

    public boolean deleteProductById(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM belts WHERE id = :id";
            return h.createUpdate(sql).bind("id", id).execute() > 0;
        });
    }


    public void saveImage(int beltId, int variantId, String imageType, String imagePath) {
        JDBIConnect.get().useHandle(handle -> {
            String sql = "INSERT INTO imageEntry (beltId,variantId, imageType, imagePath) " +
                    "VALUES (:beltId,:variantId, :imageType, :imagePath)";
            handle.createUpdate(sql)
                    .bind("beltId", beltId)
                    .bind("variantId", variantId)
                    .bind("imageType", imageType)  // 'main', 'extra', or 'description'
                    .bind("imagePath", imagePath)
                    .execute();
        });
    }


    public int updateImage(int beltId, int variantId, String imageType) {
        return JDBIConnect.get().withHandle(handle -> {
            String sql = " DELETE FROM imageEntry WHERE variantId = :variantId AND imageType = :imageType AND beltId =:beltId";
            return handle.createUpdate(sql)
                    .bind("variantId", variantId)
                    .bind("beltId", beltId)
                    .bind("imageType", imageType)
                    .execute();
        });
    }


    public int getLatestProductId() {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id FROM belts ORDER BY id DESC LIMIT 1";
            return h.createQuery(sql).mapTo(Integer.class).findFirst().orElse(0);
        });
    }

    public List<String> getDescImage(int variantId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT imagePath from imageEntry where variantId = :variantId AND imageType = 'description'";
            return h.createQuery(sql).bind("variantId", variantId).mapTo(String.class).list();
        });
    }

    public List<Belts> getRandomBelts() {
        String sql = "SELECT * FROM belts ORDER BY RAND() LIMIT 4";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Belts.class)
                        .list()
        );
    }


    public void saveBeltView(int beltId) {
        JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO beltViews (beltId, viewDate, viewCount)" +
                    "VALUES (:beltId, CURDATE(), 1)" +
                    "ON DUPLICATE KEY UPDATE viewCount = viewCount + 1;";
            return h.createUpdate(sql).bind("beltId", beltId).execute() > 0;
        });
    }

    public List<Belts> getBeltsByViewCount() {
        String sql = "SELECT b.* FROM belts b " +
                "JOIN beltViews bv ON b.id = bv.beltId " +
                "ORDER BY bv.viewCount DESC " +
                "LIMIT 4";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Belts.class)
                        .list()
        );
    }


    public boolean isUserPurchased(int beltId, int userId, int variantId) {
        String sql = "SELECT EXISTS (" +
                "    SELECT 1 " +
                "    FROM orders o " +
                "    INNER JOIN orderDetails od ON o.id = od.orderId " +
                "    WHERE o.userID = :userId " +
                "      AND od.beltId = :beltId " +
                "      AND od.variantId = :variantId" +
                ")";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("userId", userId)
                        .bind("beltId", beltId)
                        .bind("variantId", variantId)
                        .mapTo(Boolean.class)
                        .one()
        );
    }


    public List<BeltVariant> findVariants(Integer beltId, String color, String size, Integer variantId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM beltVariants WHERE 1=1");

        if (beltId != null) {
            sql.append(" AND beltId = :beltId");
        }
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
            Query query = handle.createQuery(sql.toString());

            if (beltId != null) {
                query.bind("beltId", beltId);
            }
            if (color != null && !color.isEmpty()) {
                query.bind("color", color);
            }
            if (size != null && !size.isEmpty()) {
                query.bind("size", size);
            }
            if (variantId != null) {
                query.bind("variantId", variantId);
            }

            return query.mapToBean(BeltVariant.class).list();
        });
    }


    public List<String> getVariantImages(int variantId) {
        String sql = "SELECT imagePath FROM imageEntry WHERE variantId = :variantId";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("variantId", variantId)
                        .mapTo(String.class)
                        .list()
        );
    }

    public int getTotalSold(int beltId) {
        String sql = "SELECT SUM(od.quantity) " +
                "FROM orderDetails od " +
                "JOIN beltVariants bv ON od.variantId = bv.id " +
                "WHERE bv.beltId = :beltId";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("beltId", beltId)
                        .mapTo(Integer.class)
                        .findOne()
                        .orElse(0)
        );
    }

    public int getLatestVariantId() {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id FROM beltVariants ORDER BY id DESC LIMIT 1";
            return h.createQuery(sql).mapTo(Integer.class).findFirst().orElse(0);
        });
    }
}
