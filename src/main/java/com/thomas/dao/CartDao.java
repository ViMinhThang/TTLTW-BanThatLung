package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.CartItem;

import java.util.List;

public class CartDao {

    public boolean addToCart(CartItem cartItem, boolean isBuyNow) {
        return JDBIConnect.get().withHandle(h -> {
            String sql;
            if (isBuyNow) {
                sql = "INSERT INTO cartItems(userId, beltId, variantId, quantity, price, buyNow) " +
                        "VALUES(:userId, :beltId, :variantId, :quantity, :price, 1)";
            } else {
                sql = "INSERT INTO cartItems(userId, beltId, variantId, quantity, price) " +
                        "VALUES(:userId, :beltId, :variantId, :quantity, :price)";
            }

            return h.createUpdate(sql)
                    .bind("userId", cartItem.getUserId())
                    .bind("beltId", cartItem.getBeltId())
                    .bind("variantId", cartItem.getVariantId())
                    .bind("quantity", cartItem.getQuantity())
                    .bind("price", cartItem.getPrice())
                    .execute() > 0;
        });
    }


    public List<CartItem> getCartItems(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM cartItems WHERE userId=:userId";
            return h.createQuery(sql).bind("userId", userId).mapToBean(CartItem.class).list();
        });
    }

    public boolean updateQuantity(int userId, int variantId, int beltId, int quantity) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE cartItems SET quantity=:quantity WHERE userId=:userId AND beltId=:beltId AND variantId=:variantId";
            return h.createUpdate(sql).bind("quantity", quantity)
                    .bind("userId", userId)
                    .bind("beltId", beltId)
                    .bind("variantId", variantId).execute() > 0;
        });
    }

    public boolean removeItem(int userId, int variantId, int beltId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM cartItems WHERE userId=:userId AND beltId=:beltId AND variantId=:variantId";
            return h.createUpdate(sql)
                    .bind("userId", userId)
                    .bind("beltId", beltId)
                    .bind("variantId", variantId).execute() > 0;
        });
    }

    public boolean deleteCartBuyNow(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM cartitems WHERE userId=:userId AND buyNow=1";
            return h.createUpdate(sql).bind("userId", userId).execute() > 0;
        });
    }
}
