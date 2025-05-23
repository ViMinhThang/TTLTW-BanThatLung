package com.thomas.services;

import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.PurchasesDao;
import com.thomas.dao.TransactionsDao;
import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Inventory;
import com.thomas.dao.model.Purchases;
import com.thomas.dao.SupplierDao;
import com.thomas.dao.model.Transactions;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseService {
    PurchasesDao dao;
    SupplierDao supplierDao;
    TransactionsDao transactionsDao;
    BeltVariantDao beltVariantDao;

    public PurchaseService() {
        this.dao = new PurchasesDao();
        this.supplierDao = new SupplierDao();
        this.transactionsDao = new TransactionsDao();
        this.beltVariantDao = new BeltVariantDao();
    }

    public List<Purchases> getPurchases(Integer id) {
        return dao.getPurchases(id);
    }

    public boolean createPurchase(Purchases purchases) {
        Transactions transactions = new Transactions();
        transactions.setTransactionType("IMPORT - " + purchases.getSupplierName());
        transactions.setBeltId(purchases.getBeltId());
        transactions.setQuantity(purchases.getQuantity());
        transactions.setTransactionDate(LocalDateTime.now());
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setUpdatedAt(LocalDateTime.now());
        transactions.setBeltVariantId(purchases.getBeltVariantId());
        transactionsDao.addTransactions(transactions);
        BeltVariant v = beltVariantDao.findVariants(purchases.getBeltId(), null, null, purchases.getBeltVariantId()).get(0);
        beltVariantDao.saveVariants(v);
        return dao.createPurchases(purchases);
    }

    public boolean updatePurchase(Purchases purchases) {
        return dao.updatePurchases(purchases);
    }

    public boolean deletePurchase(Purchases purchases) {
        return dao.deletePurchases(purchases);
    }

    public void setsupplierName(Purchases purchases) {
        purchases.setSupplierName(dao.findSupplierName(purchases.getSupplierId(), purchases.getBeltVariantId(), purchases.getPurchaseDate()));
    }

    public int findBeltId(String name) {
        return dao.findBeltId(name);
    }

    public void setBeltName(Purchases purchases) {
        purchases.setBeltName(dao.findBeltName(purchases.getSupplierId(), purchases.getBeltVariantId(), purchases.getPurchaseDate(), purchases.getBeltId()));
    }

    public int getSupplierId(String name) {
        return dao.findSupplierId(name);
    }

    public int getVariantId(String beltName, String size, String color) {
        int colorId = beltVariantDao.findColorByName(color);
        int sizeId = beltVariantDao.findSizeByName(size);
        return dao.findVariantId(beltName, colorId, sizeId);
    }

    public boolean addInventory(int beltId, int variantId, int stockQuantity) {
        Inventory inventory = dao.checkInventory(beltId, variantId);
        if (inventory != null) {
            inventory.setStockQuantity(inventory.getStockQuantity() + stockQuantity);
            return dao.updateInventory(inventory);
        } else {
            return dao.addInventory(new Inventory(beltId, variantId, stockQuantity));
        }
    }

    public List<Inventory> getInventory(Integer beltId, Integer variantId) {
        return dao.getInventory(beltId, variantId);
    }

    public void setInventoryNames(Inventory inventory) {
        String beltName = dao.findInventoryBelt(inventory.getBeltId(), inventory.getVariantId());
        inventory.setBeltName(beltName != null ? beltName : "N/A");

        int[] variantData = dao.findInventoryVariant(inventory.getBeltId(), inventory.getVariantId());
        if (variantData != null && variantData.length >= 2) {
            String size = dao.getSizeName(variantData[0]);
            String color = dao.getColorName(variantData[1]);
            inventory.setVariantName(List.of(
                    size != null ? size : "Unknown",
                    color != null ? color : "Unknown"
            ));
        } else {
            inventory.setVariantName(List.of("Unknown", "Unknown"));
        }
    }

    public boolean updateInventory(String name, int quantity, String color, String size) {
        int beltId = dao.findBeltId(name);
        int colorId = beltVariantDao.findColorByName(color);
        int sizeId = beltVariantDao.findSizeByName(size);
        BeltVariant v = beltVariantDao.findVariants(beltId, colorId, sizeId, null).get(0);
        Inventory i = new Inventory(beltId, v.getId(), quantity);
        i.setId(dao.findIventoryId(beltId, v.getId()));
        return dao.updateInventory(i);
    }

    public List<String> getSupplierName(String keyword) {
        return dao.getSupplierNames(keyword);
    }

    public List<String> getSupplierProducts(String keyword) {
        return dao.getSupplierProducts(keyword);
    }

    public int findSupplierId(String supplier) {
        return dao.findSupplierId(supplier);
    }

    public void deleteAllStock() {
        dao.deleteAllStock();
    }

    public boolean insertOne(Inventory inventory) {
        return dao.insertOne(inventory);
    }
}
