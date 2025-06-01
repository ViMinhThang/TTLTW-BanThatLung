package com.thomas.services;

import com.thomas.dao.CartDao;
import com.thomas.dao.model.CartItem;

import java.util.List;

public class CartService {
    CartDao cartDao;

    public CartService() {
        cartDao = new CartDao();
    }

    public boolean addToCart(CartItem item, boolean isBuyNow) {
        return cartDao.addToCart(item, isBuyNow);
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

    public boolean deleteBuyNow(int userId) {
        return cartDao.deleteCartBuyNow(userId);
    }

    public int checkInventory(int beltId, int variantId) {
        int cartState = cartDao.checkCartQuantity(beltId, variantId);
        int inventoryState = cartDao.checkInventoryQuantity(beltId, variantId);
        return inventoryState - cartState;
    }
}
