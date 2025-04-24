package com.thomas.services;

import com.thomas.dao.model.Supplier;
import com.thomas.dao.SupplierDao;

import java.util.List;

public class SupplierService {
    SupplierDao supplierDao;

    public SupplierService() {
        supplierDao = new SupplierDao();
    }

    public List<Supplier> getSuppliers(Integer id) {
        return supplierDao.getSuppliers(id);
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierDao.updateSupplier(supplier);
    }

    public boolean deleteSupplier(Supplier supplier) {
        return supplierDao.deleteSupplier(supplier);
    }

    public boolean createSupplier(Supplier supplier) {
        return supplierDao.addSupplier(supplier);
    }
}
