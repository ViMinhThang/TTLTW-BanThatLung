package com.thomas.services;

import com.thomas.dao.BeltCategoryDao;
import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.CategoryDao;
import com.thomas.dao.ProductDao;
import com.thomas.dao.db.JDBIConnect;
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

        if (beltId == null) {
            return productDao.find(null);
        } else {
            return productDao.find(beltId);
        }
    }

    public BeltVariant findVariant(Integer beltId, Integer variantId, Integer colorId, Integer sizeId) {
        BeltVariant beltVariant = productDao.findVariants(beltId, colorId, sizeId, variantId);
        beltVariant.setImages(productDao.getProductImages(beltId));
        return beltVariant;
    }

    public List<String> getVariantImages(int variantId) {
        return productDao.getVariantImages(variantId);
    }

    public String[] getTags(int beltId) {
        return productDao.getTags(beltId);
    }

    public void saveDesc(int beltId, String description, int variantId, int colorId, int sizeId) {
        productDao.saveDesc(beltId, description, variantId, colorId, sizeId);
    }


    private int getLatestVariantIdId() {
        return productDao.getLatestVariantId();
    }

    public void saveProduct(String productName, String[] tags, double discountRate,
                            LocalDateTime releaseDate, String gender, long price,
                            String material, int isDeleted, String color, String size, int userId) {
        Belts belt = new Belts();
        belt.setName(productName);
        belt.setReleaseDate(releaseDate);
        belt.setGender(gender);
        belt.setMaterialBelt(material);
        belt.setIsDeleted(isDeleted);

        productDao.createAndSaveLog(belt, userId);

        int beltId = getLatestProductId();
        int colorId = beltVariantDao.findColorByName(color);
        int sizeId = beltVariantDao.findSizeByName(size);
        BeltVariant variant = createBeltVariant(beltId, sizeId, colorId);
        variant.setPrice(price);
        beltVariantDao.createVariantAndLog(variant, userId);
        int variantId = getLatestVariantId();

        saveOrUpdateBeltCategory(tags, beltId, variantId);
    }

    public void updateProduct(int id, String productName, String[] tags, double discountRate,
                              LocalDateTime releaseDate, String gender, long price,
                              String material, int isDeleted, String color, String size, Integer variant, int userId, int colorId, int sizeId) {
        Belts belt = productDao.find(id).get(0);
        belt.setName(productName);
        belt.setReleaseDate(releaseDate);
        belt.setGender(gender);
        belt.setMaterialBelt(material);
        belt.setIsDeleted(isDeleted);
        belt.setDiscountRate(discountRate);

        productDao.updateProductAndLog(belt, userId);
        BeltVariant beltVariant = findVariant(id, variant, colorId, sizeId);
        int updatedColorId = beltVariantDao.findColorByName(color);
        int updatedSizeId = beltVariantDao.findSizeByName(size);
        updateBeltVariant(beltVariant, updatedColorId, updatedSizeId);

        saveOrUpdateBeltCategory(tags, id, variant);
    }

    private BeltVariant createBeltVariant(int beltId, int size, int color) {
        BeltVariant variant = new BeltVariant();
        variant.setBeltId(beltId);
        variant.setCreatedAt(LocalDateTime.now());
        variant.setUpdatedAt(LocalDateTime.now());
        variant.setSizeId(size);
        variant.setColorId(color);
        return variant;
    }

    private void updateBeltVariant(BeltVariant variant, int size, int color) {
        variant.setColorId(color);
        variant.setColorId(size);
        variant.setUpdatedAt(LocalDateTime.now());
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

    public void createVariant(int productId, String color, String size, LocalDateTime createdAt, long price) {
        int colorId = beltVariantDao.findColorByName(color);
        int sizeId = beltVariantDao.findSizeByName(size);
        BeltVariant variant = new BeltVariant(productId, sizeId, colorId, price, createdAt, createdAt);
        beltVariantDao.createVariant(variant);
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

    public boolean deleteProductVariant(int productId, int variantId, int userId) {
        return beltVariantDao.deleteVariantAndLog(productId, variantId, userId);
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
            long price = belt.getBeltVariant().getPrice();
            if (price > min && price < max) {
                filteredList.add(belt);
            }
        }
        return filteredList;
    }

    public List<Belts> getNewArrivals() {
        List<Belts> beltsList = productDao.find(null);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Belts b = new Belts(belt);
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
        Collections.shuffle(display);
        display.sort(new Comparator<Belts>() {
            @Override
            public int compare(Belts o1, Belts o2) {
                return o2.getReleaseDate().compareTo(o1.getReleaseDate());
            }
        });


        return display;
    }

    public int totalSold(int beltId) {
        return productDao.getTotalSold(beltId);
    }

    public List<Belts> mostPopular() {
        List<Belts> beltsList = productDao.find(null);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Belts b = new Belts(belt);
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
        Collections.shuffle(display);
        display.sort(new Comparator<Belts>() {
            @Override
            public int compare(Belts o1, Belts o2) {
                return Integer.compare(o1.getTotalSold(), o2.getTotalSold());
            }
        });
        return display;
    }

    public List<Belts> getDiscountBelts() {
        List<Belts> beltsList = productDao.find(null);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            if (belt.getDiscountRate() > 0) {
                Belts b = new Belts(belt);
                Integer[] variantId = productDao.getAllVariantId(belt.getId());
                for (int i : variantId) {
                    BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                    beltVariant.setImages(getVariantImages(beltVariant.getId()));
                    b.setBeltVariant(beltVariant);
                    display.add(b);
                }
            }

        }
        Collections.shuffle(display);
        display.sort(new Comparator<Belts>() {
            @Override
            public int compare(Belts o1, Belts o2) {
                return Integer.compare(o1.getTotalSold(), o2.getTotalSold());
            }
        });
        return display;
    }

//    public List<Belts> outOfStockBelt() {
//        List<Belts> beltsList = productDao.find(null);
//        beltsList.forEach(b -> b.setBeltVariants(findVariants(b.getId(), null, null, null)));
//        beltsList.forEach(b -> b.getBeltVariants().forEach(v -> v.setImages(productDao.getVariantImages(v.getId()))));
//        return beltsList.stream().filter(b -> b.getBeltVariants().stream().anyMatch(v -> v.getStockQuantity() == 0)).collect(Collectors.toList());
//    }

    public List<Belts> hotSelling() {
        List<Belts> beltsList = productDao.find(null);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Belts b = new Belts(belt);
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                b.setTotalSold(productDao.getTotalSold(belt.getId()));
                display.add(b);
            }
        }
        Collections.shuffle(display);
        display.sort(new Comparator<Belts>() {
            @Override
            public int compare(Belts o1, Belts o2) {
                return o1.getTotalSold() - o2.getTotalSold();
            }
        });
        return display;
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

    public boolean deleteProduct(int beltId, Integer variantId, int userId) {
        return productDao.deleteProductAndLog(beltId, userId) && beltVariantDao.deleteVariantAndLog(beltId, null, userId);
    }

    public List<Belts> searchProduct(String query) {
        List<Belts> beltsList = productDao.search(query);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Belts b = new Belts(belt);
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
        Collections.shuffle(display);
        display.sort(new Comparator<Belts>() {
            @Override
            public int compare(Belts o1, Belts o2) {
                return o2.getReleaseDate().compareTo(o1.getReleaseDate());
            }
        });
        return beltsList;
    }

    public Integer[] getAllVariantId(int id) {
        return productDao.getAllVariantId(id);
    }

    public int getTotalSold(int id) {
        return productDao.getTotalSold(id);
    }

    public String findColorNameById(int id) {
        return beltVariantDao.findColorNameById(id);
    }

    public String findSizeNameById(int id) {
        return beltVariantDao.findSizeNameById(id);
    }
}
