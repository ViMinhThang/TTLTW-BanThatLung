package com.thomas.services;

import com.thomas.dao.BeltCategoryDao;
import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.CategoryDao;
import com.thomas.dao.ProductDao;
import com.thomas.dao.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    ProductDao productDao;
    CategoryDao categoryDao;
    BeltCategoryDao beltCategoryDao;
    BeltVariantDao beltVariantDao;

    public ProductService() {
        productDao = new ProductDao();
        categoryDao = new CategoryDao();
        beltCategoryDao = new BeltCategoryDao();
        beltVariantDao = new BeltVariantDao();
    }

    public List<Belts> find(Integer beltId) {
        List<Belts> beltsList = null;
        if (beltId != null) {
            beltsList = productDao.find(beltId);

        } else {
            beltsList = productDao.find(null);
        }

        for (Belts b : beltsList) {
            b.setBeltVariants(findVariants(b.getId(), null, null, null));
        }
        return beltsList;
    }

    public List<BeltVariant> findVariants(int beltId, String color, String size, Integer variantId) {
        List<BeltVariant> beltVariants = productDao.findVariants(beltId, color, size, variantId);
        for (BeltVariant v : beltVariants) {
            v.setImages(getVariantImages(v.getId()));
        }
        return beltVariants;
    }

    public List<String> getVariantImages(int variantId) {
        return productDao.getVariantImages(variantId);
    }

    public String[] getTags(int beltId) {
        return productDao.getTags(beltId);
    }

    public void saveDesc(int beltId, String description, int variantId) {
        productDao.saveDesc(beltId, description, variantId);
    }


    private int getLatestVariantIdId() {
        return productDao.getLatestVariantId();
    }

    public void saveProduct(String productName, String[] tags, double discountRate,
                            LocalDateTime releaseDate, String gender, double price, int stockQuantity,
                            String material, int isDeleted, String color, String size) {
        Belts belt = new Belts();
        belt.setName(productName);
        belt.setPrice(price);
        belt.setReleaseDate(releaseDate);
        belt.setGender(gender);
        belt.setMaterialBelt(material);
        belt.setIsDeleted(isDeleted);

        productDao.createProduct(belt);

        int beltId = getLatestProductId();

        BeltVariant variant = createBeltVariant(beltId, size, color, stockQuantity);

        beltVariantDao.createVariant(variant);
        int variantId = getLatestVariantId();

        saveOrUpdateBeltCategory(tags, beltId, variantId);
    }

    public void updateProduct(int id, String productName, String[] tags, double discountRate,
                              LocalDateTime releaseDate, String gender, double price, int stockQuantity,
                              String material, int isDeleted, String color, String size, Integer variant) {
        Belts belt = productDao.find(id).get(0);
        belt.setName(productName);
        belt.setPrice(price);
        belt.setReleaseDate(releaseDate);
        belt.setGender(gender);
        belt.setMaterialBelt(material);
        belt.setIsDeleted(isDeleted);
        belt.setDiscountRate(discountRate);

        productDao.updateProduct(belt);
        BeltVariant beltVariant = beltVariantDao.findVariants(id, null, null, variant).get(0);
        updateBeltVariant(beltVariant, color, size, stockQuantity);

        saveOrUpdateBeltCategory(tags, id, variant);
    }

    private BeltVariant createBeltVariant(int beltId, String size, String color, int stockQuantity) {
        BeltVariant variant = new BeltVariant();
        variant.setBeltId(beltId);
        variant.setCreatedAt(LocalDateTime.now());
        variant.setUpdatedAt(LocalDateTime.now());
        variant.setSize(size);
        variant.setColor(color);
        variant.setStockQuantity(stockQuantity);
        return variant;
    }

    private void updateBeltVariant(BeltVariant variant, String color, String size, int stockQuantity) {
        variant.setColor(color);
        variant.setSize(size);
        variant.setUpdatedAt(LocalDateTime.now());
        variant.setStockQuantity(stockQuantity);
        beltVariantDao.saveVariants(variant);
    }

    private void saveOrUpdateBeltCategory(String[] tags, int beltId, int variantId) {
        for (String tag : tags) {
            Category category = getOrCreateCategory(tag);
            if (category != null) {
                getOrCreateBeltCategory(beltId, category.getId(), variantId);
            }
        }
    }


    public void saveVariant(BeltVariant beltVariant) {
        beltVariantDao.saveVariants(beltVariant);
    }

    public Category getOrCreateCategory(String tag) {
        Category category = categoryDao.getCategory(tag);
        if (category == null) {
            if (!categoryDao.createCategory(tag)) {
                throw new RuntimeException("Failed to create or retrieve category: " + tag);
            }
            category = categoryDao.getCategory(tag);
        }
        return category;
    }

    public BeltCategory getOrCreateBeltCategory(int beltId, int categoryId, int variantId) {
        BeltCategory bc = beltCategoryDao.getBeltCategory(beltId, categoryId);
        if (bc == null) {
            if (!beltCategoryDao.createBeltCategory(beltId, categoryId, variantId)) {
                throw new RuntimeException("Failed to create or retrieve category: " + beltId + " " + categoryId);
            }
            bc = beltCategoryDao.getBeltCategory(beltId, categoryId);
        }
        return bc;
    }

    public boolean deleteProductVariant(int productId, int variantId) {
        return beltVariantDao.deleteVariant(productId, variantId);
    }

    public void saveImagePath(int beltId, String filePath, List<String> extraImages, int variantId) {
        if (extraImages != null && !extraImages.isEmpty()) {
            // Save the main image
            productDao.saveImage(beltId, variantId, "main", filePath);

            // Save extra images
            for (String extraImage : extraImages) {
                productDao.saveImage(beltId, variantId, "extra", extraImage);
            }
        }
    }

    public void saveDescImg(int beltId, List<String> filePaths, int variantId) {
        for (String descImg : filePaths) {
            productDao.saveImage(beltId, variantId, "description", descImg);
        }
    }

    public int getLatestProductId() {
        return productDao.getLatestProductId();
    }

    public void saveOrUpdateImagePath(int beltId, String filePath, List<String> extraImages, int variantId, boolean isUpdate) {

        if (filePath != null && !filePath.isEmpty()) {
            if (isUpdate) {
                productDao.updateImage(beltId, variantId, "main");
            }
            productDao.saveImage(beltId, variantId, "main", filePath);
        }

        // Lưu hoặc cập nhật ảnh phụ (extra images)
        if (extraImages != null && !extraImages.isEmpty()) {
            if (isUpdate) {
                productDao.updateImage(beltId, variantId, "extra");
            }
            for (String extraImage : extraImages) {
                productDao.saveImage(beltId, variantId, "extra", extraImage);
            }
        }
    }

    public List<String> getAllDescImage(int beltId) {
        return productDao.getDescImage(beltId);
    }

    public List<Belts> getRandomBelts() {
        return productDao.getRandomBelts();
    }

    public void saveBeltView(int beltId) {
        productDao.saveBeltView(beltId);
    }

    public List<Belts> getBeltByViewCount() {
        return productDao.getBeltsByViewCount();
    }


    public List<Belts> filterProduct(List<Belts> list, double min, double max) {
        List<Belts> filteredList = new ArrayList<>();
        for (Belts belt : list) {
            if (belt.getPrice() > min && belt.getPrice() < max) {
                filteredList.add(belt);
            }
        }
        return filteredList;
    }

    public List<Belts> getNewArrivals() {
        List<Belts> beltsList = productDao.find(null);
        beltsList.forEach(b -> b.setBeltVariants(findVariants(b.getId(), null, null, null)));
        beltsList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productDao.getVariantImages(v.getId()))));
        beltsList.sort(new Comparator<Belts>() {

            @Override
            public int compare(Belts o1, Belts o2) {
                return o2.getReleaseDate().compareTo(o1.getReleaseDate());
            }
        });
        return beltsList;
    }

    public int totalSold(int beltId) {
        return productDao.getTotalSold(beltId);
    }

    public List<Belts> mostPopular() {
        List<Belts> beltsList = productDao.find(null);
        beltsList.forEach(b -> b.setBeltVariants(findVariants(b.getId(), null, null, null)));
        beltsList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productDao.getVariantImages(v.getId()))));
        beltsList.forEach(b -> b.setTotalSold(productDao.getTotalSold(b.getId())));
        return beltsList;
    }

    public List<Belts> getDiscountBelts() {
        List<Belts> beltsList = productDao.find(null);
        beltsList.forEach(b -> b.setBeltVariants(findVariants(b.getId(), null, null, null)));
        beltsList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productDao.getVariantImages(v.getId()))));
        return beltsList.stream().filter(b -> b.getDiscountRate() != 0).collect(Collectors.toList());
    }

    public List<Belts> outOfStockBelt() {
        List<Belts> beltsList = productDao.find(null);
        beltsList.forEach(b -> b.setBeltVariants(findVariants(b.getId(), null, null, null)));
        beltsList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productDao.getVariantImages(v.getId()))));
        return beltsList.stream().filter(b -> b.getBeltVariants().stream().anyMatch(v -> v.getStockQuantity() == 0)).collect(Collectors.toList());
    }

    public List<Belts> hotSelling() {
        List<Belts> beltsList = productDao.find(null);
        beltsList.forEach(b -> b.setBeltVariants(findVariants(b.getId(), null, null, null)));
        beltsList.forEach(b -> b.setTotalSold(productDao.getTotalSold(b.getId())));
        beltsList.sort(new Comparator<Belts>() {
            @Override
            public int compare(Belts o1, Belts o2) {
                return o1.getTotalSold() - o2.getTotalSold();
            }
        });
        return beltsList;
    }

    public List<Category> findCategory(int beltId, int variantId) {
        return categoryDao.find(beltId, variantId);
    }

    public boolean isUserPurchased(int beltId, int userId, int variantId) {
        return productDao.isUserPurchased(beltId, userId, variantId);
    }

    public int getLatestVariantId() {
        return productDao.getLatestVariantId();
    }
}
