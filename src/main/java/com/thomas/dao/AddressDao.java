package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Address;

import java.util.List;

public class AddressDao {
    public AddressDao() {

    }

    private static final String TOKEN = "332d8f6e-17c7-11f0-ae25-deeae188dbc1";

    public boolean insertOne(Address address) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO addresses (userId,fullname,phone,provinceId,districtId,wardId,addressDetail) VALUES(:userId,:fullname,:phone,:province,:district,:ward,:addressDetail)";
            return h.createUpdate(sql)
                    .bind("userId", address.getUserId())
                    .bind("province", address.getProvinceId())
                    .bind("district", address.getDistrictId())
                    .bind("ward", address.getWardId())
                    .bind("fullname", address.getFullName())
                    .bind("phone", address.getPhone())
                    .bind("addressDetail", address.getAddressDetail()).execute() > 0;
        });
    }

    public List<Address> findAddressByUserId(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM addresses WHERE userId = :userId";
            return h.createQuery(sql)
                    .bind("userId", userId)
                    .mapToBean(Address.class).list();
        });
    }

    public Boolean setAllUnuse(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE addresses SET isUse =0 WHERE userId = :userId";
            return h.createUpdate(sql)
                    .bind("userId", userId).execute() > 0;
        });
    }

    public Integer setDefault(int addressId, int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE addresses SET isUse = 1 WHERE userId = :userId AND id = :addressId";
            return h.createUpdate(sql).bind("userId", userId).bind("addressId", addressId).execute();
        });
    }

    public String findProvinceName(int provinceId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT name FROM provinces p JOIN districts d  WHERE id = :provinceId";
            return h.createQuery(sql).bind("provinceId", provinceId).mapTo(String.class).first();
        });
    }

    public String findDistrictName(int districtId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT name FROM districts WHERE id = :districtId";
            return h.createQuery(sql).bind("districtId", districtId).mapTo(String.class).first();
        });
    }

    public String findWardName(int wardId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT name FROM wards WHERE id = :wardId";
            return h.createQuery(sql).bind("wardId", wardId).mapTo(String.class).first();
        });
    }
}
