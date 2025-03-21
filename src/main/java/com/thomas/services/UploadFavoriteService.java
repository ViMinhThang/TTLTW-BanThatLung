package com.thomas.services;

import com.thomas.dao.FavoriteDao;
import com.thomas.dao.ProductDao;
import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Favorite;
import com.thomas.dao.model.Belts;

import java.util.ArrayList;
import java.util.List;

public class UploadFavoriteService {
    FavoriteDao favoriteDao;
    ProductDao productDao;

    public UploadFavoriteService() {
        favoriteDao = new FavoriteDao();
        productDao = new ProductDao();
    }

    public List<Belts> getFavoritesBeltByUserId(int userId) {
        List<Belts> beltsList = favoriteDao.getAllFavoriteBelts(userId);
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
                return false;
            }
        }
        boolean isAdded = favoriteDao.addFavoriteByUserId(userId, beltId, variantId);
        Favorite favorite = favoriteDao.getLatestFavorite(userId, beltId, variantId);
        return isAdded && favoriteDao.addFavoriteByUserIdManyToMany(favorite);
    }

    public boolean deleteFavoriteBelt(int userId, int beltId, int variantId) {
        return favoriteDao.removeFavoriteBelt(userId, beltId,variantId);
    }
}
