package com.thomas.services;

import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.PurchasesDao;
import com.thomas.dao.TransactionsDao;
import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Purchases;
import com.thomas.dao.SupplierDao;
import com.thomas.dao.model.Transactions;

import java.time.LocalDate;
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
        return dao.findVariantId(beltName, color, size);
    }
}
