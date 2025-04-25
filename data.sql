INSERT INTO users (name, email, dateOfBirth, password, image, isDeleted, gender, phoneNumber, role, token, isActive)
VALUES ('John Doe', 'john.doe@example.com', '1990-05-15', '202cb962ac59075b964b07152d234b70', '/images/users/john.jpg',
        0, 'Male', 1234567890, 1, NULL, 1),
       ('Jane Smith', 'jane.smith@example.com', '1995-08-22', '202cb962ac59075b964b07152d234b70',
        '/images/users/jane.jpg', 0, 'Female', 9876543210, 2, NULL, 1),
       ('Alice Brown', 'alice.brown@example.com', '1988-12-10', '202cb962ac59075b964b07152d234b70', NULL, 0, 'Female',
        1122334455, 1, NULL, 1),
       ('Bob Johnson', 'bob.johnson@example.com', '1992-07-03', '202cb962ac59075b964b07152d234b70',
        '/images/users/bob.jpg', 0, 'Male', 5566778899, 2, NULL, 1),
       ('Test', 'titphong012@gmail.com', '2004-01-01', '098f6bcd4621d373cade4e832627b4f6', '/images/users/john.jpg', 0,
        'Male', 0123456789, 1, NULL, 1);

INSERT INTO `Groups` (groupName)
VALUES ('admin');
INSERT INTO `Groups` (groupName)
VALUES ('editor');
INSERT INTO `Groups` (groupName)
VALUES ('viewer');

INSERT INTO UserGroups (userId, groupId)
VALUES (1, 1);
INSERT INTO UserGroups (userId, groupId)
VALUES (2, 2);
INSERT INTO UserGroups (userId, groupId)
VALUES (3, 3);


INSERT INTO Resources (resourceName)
VALUES ('adminPage');
INSERT INTO Resources (resourceName)
VALUES ('manageUsers');
INSERT INTO Resources (resourceName)
VALUES ('manageProducts');
INSERT INTO Resources (resourceName)
VALUES ('manageCoupons');
INSERT INTO Resources (resourceName)
VALUES ('manageOrders');
INSERT INTO Resources (resourceName)
VALUES ('viewReports');

INSERT INTO Permissions (permissionName)
VALUES ('read');
INSERT INTO Permissions (permissionName)
VALUES ('write');
INSERT INTO Permissions (permissionName)
VALUES ('execute');
INSERT INTO Permissions (permissionName)
VALUES ('access');

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 1, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 1, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 1, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 1, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 2, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 2, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 2, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 2, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 3, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 3, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 3, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 3, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 4, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 4, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 4, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 4, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 5, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 5, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 5, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 5, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 6, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 6, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 6, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (1, 6, 4);


INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 1, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 1, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 1, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 1, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 5, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 5, 2);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 5, 3);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (2, 5, 4);

INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (3, 1, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (3, 2, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (3, 3, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (3, 4, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (3, 5, 1);
INSERT INTO GroupPermissions (groupId, resourceId, permissionId)
VALUES (3, 6, 1);

INSERT INTO belts (name, gender, releaseDate, discountRate, materialBelt)
VALUES ('Classic Leather Belt', 'Male', '2024-01-10', 10.0, 'Cowhide'),
       ('Elegant Slim Belt', 'Female', '2024-02-15', 15.0, 'PU Leather'),
       ('Vintage Brown Belt', 'Male', '2024-03-05', 0.0, 'Genuine Leather'),
       ('Modern Wide Belt', 'Unisex', '2024-01-25', 5.0, 'Synthetic'),
       ('Studded Punk Belt', 'Female', '2024-04-01', 20.0, 'Faux Leather');
INSERT INTO beltVariants (beltId, sizeId, colorId, description, price)
VALUES (1, 1, 1, 'Classic style with polished buckle', 250000),
       (2, 2, 2, 'Slim belt for dresses and skirts', 220000),
       (3, 1, 3, 'Brown vintage look', 270000),
       (4, 3, 4, 'Wide modern belt for streetwear', 300000),
       (5, 2, 5, 'Punk-inspired look with studs', 320000);
INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES (1, 1, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f1c60ec31ec145bfa6879649511a6d98_9366/Reversible_Webbing_Belt_Grey_IQ2909_01_standard.jpg'),
       (2, 2, 'main',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/805475e310b9490ca12fb43b58f694b6_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM1.jpg'),
       (3, 3, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/94b43d1c2f1b4852bd3272d7756ac9ec_9366/Reversible_Webbing_Belt_Blue_IY4418_41_detail_hover.jpg'),
       (4, 4, 'main',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/1e2d6ab5d120415bb0d80db4c6c7b40f_9366/3-Stripes_Tour_Belt_Black_JJ3847_01_00_standard.jpg'),
       (5, 5, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/78ebce5408c94e9abcf484febd66898f_9366/Players_Reversible_Belt_Brown_JJ3852_01_00_standard.jpg');


INSERT INTO paymentMethods (name, isActive)
VALUES ('COD', 1),
       ('MoMo', 1),
       ('VNPay', 1);


INSERT INTO suppliers (name, contactInfo)
VALUES ('BeltPro Vietnam', 'SĐT: 0909090909, Email: contact@beltpro.vn, Địa chỉ: 123 Nguyễn Trãi, Q.5, TP.HCM'),
       ('Thắt Lưng Da Cao Cấp Hà Nội',
        'SĐT: 0911223344, Email: info@thatlunghanoi.vn, Địa chỉ: 56 Lê Duẩn, Hoàn Kiếm, Hà Nội'),
       ('CTY TNHH Dây Nịt Nam Quốc',
        'SĐT: 0988123456, Email: sales@namquocbelts.com, Địa chỉ: 22 Lý Thường Kiệt, Đà Nẵng'),
       ('LeatherStyle Co., Ltd', 'SĐT: 0977665544, Email: support@leatherstyle.com, Địa chỉ: 77 Pasteur, Q.1, TP.HCM'),
       ('Phụ Kiện Thời Trang Belto', 'SĐT: 0966998877, Email: belto@fashion.vn, Địa chỉ: 88 Trần Hưng Đạo, Cần Thơ');


INSERT INTO purchases (supplierId, beltId, beltVariantId, quantity, purchaseDate)
VALUES (1, 1, 1, 100, '2025-04-01 09:00:00'),
       (1, 1, 2, 50, '2025-04-02 10:30:00'),
       (2, 2, 3, 80, '2025-04-03 14:15:00'),
       (2, 2, 4, 60, '2025-04-04 16:00:00'),
       (3, 3, 5, 120, '2025-04-05 11:45:00'),
       (1, 3, 6, 75, '2025-04-06 13:20:00'),
       (2, 4, 7, 90, '2025-04-07 15:10:00'),
       (3, 4, 8, 65, '2025-04-08 17:35:00'),
       (1, 2, 9, 55, '2025-04-09 08:25:00'),
       (2, 1, 10, 110, '2025-04-10 10:50:00');

INSERT INTO colors (name, hexCode)
VALUES ('Black', '#000000'),
       ('Brown', '#8B4513'),
       ('Tan', '#D2B48C'),
       ('White', '#FFFFFF'),
       ('Navy', '#000080'),
       ('Gray', '#808080');
INSERT INTO sizes (name)
VALUES ('S'),
       ('M'),
       ('L'),
       ('XL'),
       ('XXL');
