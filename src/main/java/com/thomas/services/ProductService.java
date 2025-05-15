package com.thomas.services;

import com.thomas.dao.BeltCategoryDao;
import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.CategoryDao;
import com.thomas.dao.ProductDao;
import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.*;
import org.eclipse.tags.shaded.org.apache.xalan.templates.KeyDeclaration;

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
        beltVariant.setImages(productDao.getProductImages(beltId, variantId));
        beltVariant.setStockQuantity(productDao.getStockQuantity(beltId, variantId, colorId, sizeId));
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

    public boolean deleteProductVariant(Integer productId, int variantId, int userId) {
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
        List<Belts> beltsList = productDao.getRandomBelts();
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setColor(beltVariantDao.findColorNameById(beltVariant.getColorId()));
                beltVariant.setSize(beltVariantDao.findSizeNameById(beltVariant.getSizeId()));
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
        Collections.shuffle(display);
        return display;
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
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setColor(beltVariantDao.findColorNameById(beltVariant.getColorId()));
                beltVariant.setSize(beltVariantDao.findSizeNameById(beltVariant.getSizeId()));
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
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
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setColor(beltVariantDao.findColorNameById(beltVariant.getColorId()));
                beltVariant.setSize(beltVariantDao.findSizeNameById(beltVariant.getSizeId()));
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
                Integer[] variantId = productDao.getAllVariantId(belt.getId());
                for (int i : variantId) {
                    Belts b = new Belts(belt);
                    BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                    beltVariant.setColor(beltVariantDao.findColorNameById(beltVariant.getColorId()));
                    beltVariant.setSize(beltVariantDao.findSizeNameById(beltVariant.getSizeId()));
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
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
                BeltVariant beltVariant = findVariant(belt.getId(), i, null, null);
                beltVariant.setImages(getVariantImages(beltVariant.getId()));
                beltVariant.setColor(beltVariantDao.findColorNameById(beltVariant.getColorId()));
                beltVariant.setSize(beltVariantDao.findSizeNameById(beltVariant.getSizeId()));
                beltVariant.setStockQuantity(productDao.getStockQuantity(b.getId(), i, null, null));
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

    public boolean deleteProduct(int beltId) {
        return productDao.deleteProductById(beltId);
    }

    public List<Belts> searchProduct(String query) {
        List<Belts> beltsList = productDao.search(query);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Integer[] variantId = productDao.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
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

    public boolean updateProduct(Belts belt) {
        return productDao.updateProduct(belt);
    }

    public boolean updateVariant(BeltVariant variant) {
        return beltVariantDao.saveVariants(variant);
    }

    public void saveTags(String tags, int beltId, int variantId) {
        String[] tagList = tags.split(",");
        for (String tag : tagList) {
            categoryDao.createCategory(tag);
            int categoryId = categoryDao.getLatestCategoryId();
            beltCategoryDao.createBeltCategory(beltId, categoryId, variantId);
        }
    }

    public boolean createBelt(Belts b) {
        return productDao.createProduct(b);
    }

    public boolean createVariant(BeltVariant v) {
        return beltVariantDao.createVariant(v);
    }

    public List<BeltVariant> similarVariants(int id) {
        return beltVariantDao.similarVariants(id);
    }

    public List<Belts> sort(String sort, List<Belts> listBelt) {
        switch (sort) {
            case "desc":
                return listBelt.stream()
                        .sorted(Comparator.comparing(b -> b.getBeltVariant().getPrice(), Comparator.reverseOrder()))
                        .collect(Collectors.toList());

            case "asc":
                return listBelt.stream()
                        .sorted(Comparator.comparing(b -> b.getBeltVariant().getPrice()))
                        .collect(Collectors.toList());
            default:
                return listBelt;
        }
    }

    public BeltVariant getLatestVariant() {
        return beltVariantDao.findLatestVariant();
    }

    public boolean addDescription(BeltVariant v, String description) {
        return beltVariantDao.addDescription(v, description);
    }
}
