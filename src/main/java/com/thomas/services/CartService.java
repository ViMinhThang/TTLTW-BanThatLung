package com.thomas.services;

import com.thomas.dao.CartDao;
import com.thomas.dao.model.CartItem;

import java.util.List;

public class CartService {
    CartDao cartDao;

    public CartService() {
        cartDao = new CartDao();
    }

    public boolean addToCart(CartItem item) {
        return cartDao.addToCart(item);
    }

    public boolean removeFromCart(int userId, int variantId, int beltId) {
        return cartDao.removeItem(userId, variantId, beltId);
    }

    public boolean updateQuantity(int userId, int variantId, int beltId, int quantity) {
        return cartDao.updateQuantity(userId, variantId, beltId, quantity);
    }

    public List<CartItem> getCart(int userId) {
        return cartDao.getCartItems(userId);
    }

}
