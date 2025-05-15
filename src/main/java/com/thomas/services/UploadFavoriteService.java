package com.thomas.services;

import com.thomas.dao.FavoriteDao;
import com.thomas.dao.ProductDao;
import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Favorite;
import com.thomas.dao.model.Belts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UploadFavoriteService {
    FavoriteDao favoriteDao;
    ProductDao productDao;

    public UploadFavoriteService() {
        favoriteDao = new FavoriteDao();
        productDao = new ProductDao();
    }

    public List<Belts> getFavoritesBeltByUserId(int userId) {
        Set<Integer> beltIds = new HashSet<>();
        List<Belts> beltsList = favoriteDao.getAllFavoriteBelts(userId).stream()
                .filter(b -> beltIds.add(b.getId()))
                .collect(Collectors.toList());
        beltsList.forEach(b -> b.setBeltVariants(getFavoritesBeltVariantByBeltId(b.getId(), userId)));
        beltsList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productDao.getVariantImages(v.getId()))));
        return beltsList;
    }

    public List<BeltVariant> getFavoritesBeltVariantByBeltId(int beltId, int userId) {
        return favoriteDao.findVariantByBeltIdAndUserId(beltId, userId);
    }

    public boolean addFavoriteByUserId(int userId, int beltId, int variantId) {
        List<Belts> favoriteBelts = getFavoritesBeltByUserId(userId);
        for (Belts belt : favoriteBelts) {
            if (belt.getId() == beltId) {
                for (BeltVariant variant : belt.getBeltVariants()) {
                    if (variant.getId() == variantId) {
                        return false;
                    }
                }
            }
        }
        boolean isAdded = favoriteDao.addFavoriteByUserId(userId, beltId, variantId);
        Favorite favorite = favoriteDao.getLatestFavorite(userId, beltId, variantId);
        return isAdded && favoriteDao.addFavoriteByUserIdManyToMany(favorite);
    }

    public boolean deleteFavoriteBelt(int userId, int beltId, int variantId) {
        return favoriteDao.removeFavoriteBelt(userId, beltId, variantId);
    }
}
