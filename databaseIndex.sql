CREATE INDEX idx_orders_id ON orders (id);
CREATE INDEX idx_orders_userId ON orders (userID);
CREATE INDEX idx_orders_user_isDeleted ON orders (userID, isDeleted);
CREATE INDEX idx_orders_paymentMethodId ON orders (paymentMethodId);
CREATE INDEX idx_orders_beltId ON orders (beltId);
CREATE INDEX idx_orders_addressesId ON orders (addressesId);

CREATE INDEX idx_users_id ON users (id);
CREATE INDEX idx_paymentMethods_id ON paymentMethods (id);
CREATE INDEX idx_addresses_id ON addresses (id);

CREATE INDEX idx_usersusage_userId ON usersusage (userId);

CREATE INDEX idx_orderDetails_id ON orderDetails (id);
CREATE INDEX idx_orderDetails_orderId ON orderDetails (orderId);
CREATE INDEX idx_orderDetails_beltId ON orderDetails (beltId);
CREATE INDEX idx_orderDetails_variantId ON orderDetails (variantId);

CREATE INDEX idx_belts_id ON belts (id);
CREATE INDEX idx_beltVariants_id ON beltVariants (id);
CREATE INDEX idx_beltVariants_beltId ON beltVariants (beltId);

CREATE INDEX idx_orderDetails_variantId ON orderDetails (variantId);
CREATE INDEX idx_userGroups_userId ON userGroups (userId);
CREATE INDEX idx_userGroups_groupId ON userGroups (groupId);
CREATE INDEX idx_groupPermissions_groupId ON groupPermissions (groupId);
CREATE INDEX idx_groupPermissions_permissionId ON groupPermissions (permissionId);
CREATE INDEX idx_groupPermissions_resourceId ON groupPermissions (resourceId);
CREATE INDEX idx_permissions_permissionName ON permissions (permissionName);
CREATE INDEX idx_resources_resourceName ON resources (resourceName);
CREATE INDEX idx_groups_groupName ON groups (groupName);
CREATE INDEX idx_usersusage_userId ON usersusage (userId);
CREATE INDEX idx_cartItems_userId ON cartItems (userId);
CREATE INDEX idx_cartItems_user_belt_variant ON cartItems (userId, beltId, variantId);
