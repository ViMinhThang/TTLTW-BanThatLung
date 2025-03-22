package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Favorite;
import com.thomas.dao.model.Belts;

import java.util.List;

public class FavoriteDao {

    public List<Belts> getAllFavoriteBelts(int userId) {
        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(
                                "SELECT b.* " +
                                        "FROM beltfavorites bf " +
                                        "JOIN belts b ON bf.beltId = b.id " +
                                        "JOIN favorites f ON bf.favoriteId = f.id " +
                                        "WHERE f.userId = :userId AND b.isDeleted = 0"
                        )
                        .bind("userId", userId)
                        .mapToBean(Belts.class)
                        .list()
        );
    }


    public boolean addFavoriteByUserId(int userId, int beltId, int variantId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO favorites (userId,beltId,variantId) VALUES (:userId,:beltId,:variantId)";
            return h.createUpdate(sql).bind("userId", userId).bind("beltId", beltId).bind("variantId", variantId).execute() > 0;
        });
    }

    public boolean addFavoriteByUserIdManyToMany(Favorite favorite) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO beltFavorites (beltId,favoriteId,variantId) VALUES (:beltId,:favoriteId,:variantId)";
            return h.createUpdate(sql).bind("beltId", favorite.getBeltId()).bind("favoriteId", favorite.getId()).bind("variantId", favorite.getVariantId()).execute() > 0;
        });
    }

    public boolean removeFavoriteBelt(int userId, int beltId, int variantId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM favorites WHERE userId = :userId AND beltId = :beltId AND variantId = :variantId";
            return h.createUpdate(sql)
                    .bind("userId", userId)
                    .bind("beltId", beltId)
                    .bind("variantId", variantId)
                    .execute() > 0;
        });
    }

    public Favorite getLatestFavorite(int userId, int beltId, int variantId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM favorites WHERE userId=:userId AND beltId=:beltId AND variantId=:variantId ORDER BY id DESC LIMIT 1";
            return h.createQuery(sql).bind("userId", userId).bind("beltId", beltId).bind("variantId", variantId).mapToBean(Favorite.class).findFirst().orElse(null);
        });
    }

    public List<BeltVariant> findVariantByBeltIdAndUserId(int beltId, int userId) {
        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(
                                "SELECT bv.* " +
                                        "FROM beltfavorites bf " +
                                        "JOIN beltVariants bv ON bf.variantId = bv.id " +
                                        "JOIN favorites f ON bf.favoriteId = f.id " +
                                        "WHERE bf.beltId = :beltId AND f.userId = :userId"
                        )
                        .bind("beltId", beltId)
                        .bind("userId", userId)
                        .mapToBean(BeltVariant.class)
                        .list()
        );
    }

}
