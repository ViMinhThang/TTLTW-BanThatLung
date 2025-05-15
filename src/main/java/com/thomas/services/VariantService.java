package com.thomas.services;

import com.thomas.dao.BeltVariantDao;

import java.util.List;

public class VariantService {
    BeltVariantDao beltVariantDao;

    public VariantService() {
        this.beltVariantDao = new BeltVariantDao();
    }

    public List<String> getAllColors() {
        return beltVariantDao.getAllColors();
    }

    public List<String> getAllColorRelatedToBelt(String name) {
        return beltVariantDao.getAllColorRelatedToBeltName(name);
    }

    public List<String> getAllSizeRelatedToBelt(String name) {
        return beltVariantDao.getAllSizesRelatedToBeltName(name);
    }

    public List<String> getAllColorByNameBeltAndSize(String beltName, String size) {
        return beltVariantDao.getAllcolorsByNameBeltAndSize(beltName, size);
    }

    public List<String> getAllSizeByBeltNameAndColor(String name, String color) {
        return beltVariantDao.getAllSizeByNameBeltAndColor(name, color);

    }

    public boolean deleteVariant(int variantId) {
        return beltVariantDao.deleteVariant(null, variantId);
    }

    public List<String> getBeltNames(String keyword) {
        return beltVariantDao.getBeltNameAlike(keyword);
    }

    public int getQuantity(String name, String color, String size) {
        return beltVariantDao.getQuantity(name, color, size);
    }
}
