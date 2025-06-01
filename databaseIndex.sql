CREATE INDEX idx_belts_id ON belts(id);
CREATE INDEX idx_belts_name ON belts(name);

CREATE INDEX idx_beltVariants_beltId ON beltVariants(beltId);
CREATE INDEX idx_beltVariants_id ON beltVariants(id);
CREATE INDEX idx_beltVariants_color_size ON beltVariants(colorId, sizeId);
CREATE INDEX idx_beltVariants_variant ON beltVariants(id, colorId, sizeId);

CREATE INDEX idx_beltCategory_beltId ON beltCategory(beltId);
CREATE INDEX idx_beltCategory_categoryId ON beltCategory(categoryId);

CREATE INDEX idx_categories_id ON categories(id);

CREATE INDEX idx_imageEntry_belt_variant_type ON imageEntry(beltId, variantId, imageType);
CREATE INDEX idx_imageEntry_variantId ON imageEntry(variantId);

CREATE INDEX idx_beltViews_beltId ON beltViews(beltId);

CREATE INDEX idx_orders_userId ON orders(userId);

CREATE INDEX idx_orderDetails_belt_variant ON orderDetails(beltId, variantId);
CREATE INDEX idx_orderDetails_orderId ON orderDetails(orderId);

CREATE INDEX idx_inventory_belt_variant ON inventory(beltId, variantId);

CREATE INDEX idx_usersusage_userId ON usersusage(userId);

CREATE INDEX idx_cartitems_userid ON cartItems(userId);

CREATE INDEX idx_cartitems_belt_variant ON cartItems(beltId, variantId);

CREATE INDEX idx_cartitems_userid_buynow ON cartItems(userId, buyNow);

