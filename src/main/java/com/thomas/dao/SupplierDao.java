package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Supplier;

import java.time.LocalDateTime;
import java.util.List;

public class SupplierDao {
    public List<Supplier> getSuppliers(Integer supplierId) {
        String sql = "select * from suppliers";
        if (supplierId != null) {
            sql += " where id = " + supplierId;
        }
        String finalSql = sql;
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(finalSql).mapToBean(Supplier.class).list();
        });
    }

    public boolean updateSupplier(Supplier supplier) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE suppliers SET name=:name,contactInfo=:contactInfo,createdAt=:createdAt,updatedAt=:updatedAt WHERE id=:id";
            return h.createUpdate(sql).bind("name", supplier.getName())
                    .bind("contactInfo", supplier.getContactInfo())
                    .bind("createdAt", supplier.getCreatedAt())
                    .bind("updatedAt", LocalDateTime.now())
                    .bind("id", supplier.getId()).execute() > 0;
        });
    }

    public boolean addSupplier(Supplier supplier) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO suppliers(name,contactInfo,createdAt,updatedAt) VALUES (:name,:contactInfo,:createdAt,:updatedAt)";
            return h.createUpdate(sql).bind("name", supplier.getName())
                    .bind("contactInfo", supplier.getContactInfo())
                    .bind("createdAt", supplier.getCreatedAt())
                    .bind("updatedAt", LocalDateTime.now())
                    .execute() > 0;
        });
    }

    public boolean deleteSupplier(Supplier supplier) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM suppliers WHERE id=:id";
            return h.createUpdate(sql).bind("id", supplier.getId()).execute() > 0;
        });
    }
}
