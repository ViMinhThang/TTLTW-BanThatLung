package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Coupon;
import com.thomas.dao.model.User;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CouponDao implements UsageInterface {
    public List<Coupon> getAllCoupon() {
        List<Coupon> couponList = new ArrayList<>();
        String sql = "SELECT * FROM coupons ORDER BY id DESC";

        JDBIConnect.get().withHandle(handle -> {
            try (ResultSet rs = handle.getConnection().createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    Coupon coupon = new Coupon();
                    coupon.setId(rs.getInt("id"));
                    coupon.setCode(rs.getString("code"));
                    coupon.setDiscountRate(rs.getDouble("discountRate"));
                    coupon.setStartDate(rs.getDate("startDate").toLocalDate());
                    coupon.setEndDate(rs.getDate("endDate").toLocalDate());
                    coupon.setIsActive(rs.getInt("isActive"));
                    couponList.add(coupon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        return couponList;
    }

    public boolean deleteCouponById(int id) {
        String sql = "DELETE FROM coupons WHERE id = :id";
        return JDBIConnect.get().withHandle(h -> {
            return h.createUpdate(sql).bind("id", id).execute() > 0;
        });
    }


    public boolean deleteCouponAndLog(int couponId, int userId) {
        boolean result = deleteCouponById(couponId);
        if (result) {
            saveLogToDB(userId, "Xóa coupon" + couponId, "Đỏ");
            return true;
        } else {
            return false;
        }
    }

    public boolean createCoupon(Coupon coupon) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO coupons (code,discountRate,startDate,endDate,isActive) VALUES (:code,:discountRate,:startDate,:endDate,:isActive)";
            return h.createUpdate(sql).
                    bind("code", coupon.getCode())
                    .bind("discountRate", coupon.getDiscountRate())
                    .bind("startDate", coupon.getStartDate())
                    .bind("endDate", coupon.getEndDate())
                    .bind("isActive", coupon.getIsActive()).execute() > 0;
        });
    }

    public boolean createCouponAndUpdateUser(Coupon coupon, int userId) {
        boolean result = createCoupon(coupon);

        if (result) {
            saveLogToDB(userId, "Tạo coupon mới", "Xanh");
            return true;
        } else {
            return false;
        }
    }


    public Coupon getCouponById(int id) {
        String sql = "SELECT * FROM coupons WHERE id = :id";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("id", id).mapToBean(Coupon.class).findFirst().orElse(null);
        });
    }

    public Coupon getDiscountRate(String code) {
        String sql = "SELECT * FROM coupons WHERE code = :code";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("code", code).mapTo(Coupon.class).findFirst().orElse(null);
        });
    }

    public Coupon getCouponByCode(String code) {
        String sql = "SELECT * FROM coupons WHERE code = :code";
        return JDBIConnect.get().withHandle(h -> {
            return h.createQuery(sql).bind("code", code).mapTo(Coupon.class).findFirst().orElse(null);
        });
    }

    public boolean updateCoupon(Coupon coupon) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE coupons " +
                    "SET code = :code, discountRate = :discountRate, startDate = :startDate, " +
                    "endDate = :endDate, isActive = :isActive " +
                    "WHERE id = :id";
            return h.createUpdate(sql)
                    .bind("code", coupon.getCode())
                    .bind("discountRate", coupon.getDiscountRate())
                    .bind("startDate", coupon.getStartDate())
                    .bind("endDate", coupon.getEndDate())
                    .bind("isActive", coupon.getIsActive())
                    .bind("id", coupon.getId())
                    .execute() > 0;
        });
    }

    public boolean updateCouponAndLog(Coupon coupon, int userId) {
        boolean result = updateCoupon(coupon);

        if (result) {
            saveLogToDB(userId, "Chỉnh sửa coupon " + coupon.getCode(), "Vàng");
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void saveLogToDB(int userId, String log, String alert) {
        JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO usersusage (userId,lastActivity,label,alert) VALUES(:userId,:lastActivity,:label,:alert)";
            return h.createUpdate(sql).bind("userId", userId)
                    .bind("lastActivity", LocalDateTime.now())
                    .bind("alert", alert)
                    .bind("label", log).execute() > 0;
        });
    }
}
