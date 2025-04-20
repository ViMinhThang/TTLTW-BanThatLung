package com.thomas.services;

import com.thomas.dao.CouponDao;
import com.thomas.dao.model.Coupon;
import com.thomas.dao.model.User;

import java.time.LocalDate;
import java.util.List;

public class UploadCouponService {
    CouponDao couponDao;

    public UploadCouponService() {
        couponDao = new CouponDao();
    }

    public List<Coupon> getAllCoupon() {
        return couponDao.getAllCoupon();
    }

    public boolean deleteCoupon(int couponId, int userId) {
        return couponDao.deleteCouponAndLog(couponId, userId);
    }

    public boolean createCoupon(LocalDate startDate, LocalDate endDate, String couponCode, double discountPercentage, int isActive, int userId) {
        Coupon Coupon = new Coupon();
        if (couponDao.getCouponByCode(couponCode) != null) {
            return false;
        }
        Coupon coupon = new Coupon();
        coupon.setStartDate(startDate);
        coupon.setEndDate(endDate);
        coupon.setCode(couponCode);
        coupon.setDiscountRate(discountPercentage);
        coupon.setIsActive(isActive);
        return couponDao.createCouponAndUpdateUser(coupon, userId);
    }

    public Coupon getCoupon(int couponId) {
        return couponDao.getCouponById(couponId);
    }

    public Coupon getCouponByCode(String couponCode) {
        return couponDao.getDiscountRate(couponCode);
    }

    public boolean updateCoupon(int couponId, LocalDate startDate, LocalDate endDate, String couponCode, double discountPercentage, int isActive, int userId) {
        Coupon coupon = new Coupon();
        coupon.setStartDate(startDate);
        coupon.setEndDate(endDate);
        coupon.setCode(couponCode);
        coupon.setDiscountRate(discountPercentage);
        coupon.setId(couponId);
        coupon.setIsActive(isActive);
        return couponDao.updateCouponAndLog(coupon, userId);
    }
}
