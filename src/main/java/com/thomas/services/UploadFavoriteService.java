package com.thomas.services;

import com.thomas.dao.BeltVariantDao;
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
    ProductService productService;
    BeltVariantDao beltVariantDao;

    public UploadFavoriteService() {
        favoriteDao = new FavoriteDao();
        productDao = new ProductDao();
        productService = new ProductService();
        beltVariantDao = new BeltVariantDao();
    }

    public List<Belts> getFavoritesBeltByUserId(int userId) {
        Set<Integer> beltIds = new HashSet<>();
        List<Belts> beltsList = favoriteDao.getAllFavoriteBelts(userId).stream()
                .filter(b -> beltIds.add(b.getId()))
                .collect(Collectors.toList());
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
                BeltVariant beltVariant = productService.findVariant(belt.getId(), i, null, null);
                beltVariant.setColor(beltVariantDao.findColorNameById(beltVariant.getColorId()));
                beltVariant.setSize(beltVariantDao.findSizeNameById(beltVariant.getSizeId()));
                beltVariant.setImages(productService.getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
        return display;
    }

    public List<BeltVariant> getFavoritesBeltVariantByBeltId(int beltId, int userId) {
        return favoriteDao.findVariantByBeltIdAndUserId(beltId, userId);
    }

    public boolean addFavoriteByUserId(int userId, int beltId, int variantId) {
        boolean checked = favoriteDao.checkExistFavorite(userId);
        if (checked) {
            favoriteDao.addFavoriteByUserId(userId, beltId, variantId);
        }
        List<Belts> favoriteBelts = getFavoritesBeltByUserId(userId);
        for (Belts belt : favoriteBelts) {
            if (belt.getId() == beltId && belt.getBeltVariant().getId() == variantId) {
                return false;
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
