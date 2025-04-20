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
VALUES (101, 1);
INSERT INTO UserGroups (userId, groupId)
VALUES (102, 2);
INSERT INTO UserGroups (userId, groupId)
VALUES (103, 3);


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



INSERT INTO paymentMethods (name, isActive)
VALUES ('COD', 1),
       ('MoMo', 1),
       ('VNPay', 1);

INSERT INTO belts (name, description, price, gender, releaseDate, isDeleted, discountRate, materialBelt)
VALUES ('Classic Leather Belt', 'A premium leather belt for formal wear.', 29.99, 'Unisex', '2024-03-01', 0, 10.0,
        'Genuine Leather'),
       ('Casual Canvas Belt', 'A comfortable canvas belt for everyday use.', 19.99, 'Male', '2024-02-15', 0, 5.0,
        'Canvas'),
       ('Luxury Gold Belt', 'An elegant gold-plated belt for special occasions.', 49.99, 'Female', '2024-01-10', 0,
        15.0, 'Metal & Leather'),
       ('Sports Elastic Belt', 'A flexible and durable belt for active lifestyles.', 14.99, 'Unisex', '2024-03-20', 0,
        0.0, 'Elastic'),
       ('Classic Black Belt', 'A stylish black belt suitable for all outfits.', 25.99, 'Male', '2024-02-05', 0, 8.0,
        'Synthetic Leather');

INSERT INTO beltVariants (beltId, color, size, stockQuantity)
VALUES
    -- Classic Leather Belt (ID = 1)
    (1, 'Black', 'XS', 20),
    (1, 'Black', 'S', 30),
    (1, 'Black', 'M', 50),
    (1, 'Black', 'L', 40),
    (1, 'Black', 'XL', 25),

    (1, 'Brown', 'XS', 15),
    (1, 'Brown', 'S', 25),
    (1, 'Brown', 'M', 40),
    (1, 'Brown', 'L', 35),
    (1, 'Brown', 'XL', 20),

    -- Casual Canvas Belt (ID = 2)
    (2, 'Beige', 'XS', 10),
    (2, 'Beige', 'S', 20),
    (2, 'Beige', 'M', 30),
    (2, 'Beige', 'L', 25),
    (2, 'Beige', 'XL', 15),

    (2, 'Green', 'XS', 12),
    (2, 'Green', 'S', 18),
    (2, 'Green', 'M', 25),
    (2, 'Green', 'L', 20),
    (2, 'Green', 'XL', 10),

    -- Luxury Gold Belt (ID = 3)
    (3, 'Gold', 'XS', 8),
    (3, 'Gold', 'S', 15),
    (3, 'Gold', 'M', 20),
    (3, 'Gold', 'L', 15),
    (3, 'Gold', 'XL', 10),

    -- Sports Elastic Belt (ID = 4)
    (4, 'Blue', 'XS', 18),
    (4, 'Blue', 'S', 22),
    (4, 'Blue', 'M', 35),
    (4, 'Blue', 'L', 30),
    (4, 'Blue', 'XL', 25),

    (4, 'Red', 'XS', 15),
    (4, 'Red', 'S', 20),
    (4, 'Red', 'M', 40),
    (4, 'Red', 'L', 30),
    (4, 'Red', 'XL', 20),

    -- Classic Black Belt (ID = 5)
    (5, 'Black', 'XS', 35),
    (5, 'Black', 'S', 45),
    (5, 'Black', 'M', 55),
    (5, 'Black', 'L', 50),
    (5, 'Black', 'XL', 30);


INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES
    -- Classic Leather Belt (ID = 1)
    (1, 1, 'main',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3b7f903209fb41a681f0a588bf331876_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM1.jpg'),
    (1, 2, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ad01fc33e59144279b93f0d890dc622e_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM3_hover.jpg'),
    (1, 3, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/b425b5ce702f4ebc8f2425105efef088_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM4.jpg'),
    (1, 4, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/9621155a1d4c47a3a0ed407e135d1902_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM5.jpg'),
    (1, 5, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/9621155a1d4c47a3a0ed407e135d1902_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM5.jpg'),

    (1, 6, 'main',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3b7f903209fb41a681f0a588bf331876_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM1.jpg'),
    (1, 7, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ad01fc33e59144279b93f0d890dc622e_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM3_hover.jpg'),
    (1, 8, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/b425b5ce702f4ebc8f2425105efef088_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM4.jpg'),
    (1, 9, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/9621155a1d4c47a3a0ed407e135d1902_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM5.jpg'),
    (1, 10, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/9621155a1d4c47a3a0ed407e135d1902_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM5.jpg'),


    -- Casual Canvas Belt (ID = 2)
    (2, 11, 'main',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/805475e310b9490ca12fb43b58f694b6_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM1.jpg'),
    (2, 12, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/812e9344d3d24e519a02ff72bb3aff0b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM3_hover.jpg'),
    (2, 13, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5925aa89992541fe9db4cdd72bbea33d_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM4.jpg'),
    (2, 14, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/67281d6b30cb4162bbb4ed7bc1ffd15b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM5.jpg'),
    (2, 15, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/67281d6b30cb4162bbb4ed7bc1ffd15b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM5.jpg'),

    (2, 16, 'main',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/805475e310b9490ca12fb43b58f694b6_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM1.jpg'),
    (2, 17, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/812e9344d3d24e519a02ff72bb3aff0b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM3_hover.jpg'),
    (2, 18, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5925aa89992541fe9db4cdd72bbea33d_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM4.jpg'),
    (2, 19, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/67281d6b30cb4162bbb4ed7bc1ffd15b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM5.jpg'),
    (2, 20, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/67281d6b30cb4162bbb4ed7bc1ffd15b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM5.jpg'),


    -- Luxury Gold Belt (ID = 3)
    (3, 21, 'main',
     'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f1c60ec31ec145bfa6879649511a6d98_9366/Reversible_Webbing_Belt_Grey_IQ2909_01_standard.jpg'),
    (3, 22, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ff743cbdd53c465282facf2c9f431f7d_9366/Reversible_Webbing_Belt_Grey_IQ2909_41_detail_hover.jpg'),
    (3, 23, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/7fbdb09684bf442db91b07605ac5edd9_9366/Reversible_Webbing_Belt_Grey_IQ2909_42_detail.jpg'),
    (3, 24, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/7fbdb09684bf442db91b07605ac5edd9_9366/Reversible_Webbing_Belt_Grey_IQ2909_42_detail.jpg'),
    (3, 25, 'main',
     'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f1c60ec31ec145bfa6879649511a6d98_9366/Reversible_Webbing_Belt_Grey_IQ2909_01_standard.jpg'),

    -- Sports Elastic Belt (ID = 4)
    (4, 26, 'main',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/f09f0a1d15a340288f57d28cfe08ba5f_9366/Reversible_Webbing_Belt_Blue_IY4418_01_standard.jpg'),
    (4, 27, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/94b43d1c2f1b4852bd3272d7756ac9ec_9366/Reversible_Webbing_Belt_Blue_IY4418_41_detail_hover.jpg'),
    (4, 28, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),
    (4, 29, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),
    (4, 30, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),

    (4, 31, 'main',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/f09f0a1d15a340288f57d28cfe08ba5f_9366/Reversible_Webbing_Belt_Blue_IY4418_01_standard.jpg'),
    (4, 32, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/94b43d1c2f1b4852bd3272d7756ac9ec_9366/Reversible_Webbing_Belt_Blue_IY4418_41_detail_hover.jpg'),
    (4, 33, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),
    (4, 34, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),
    (4, 35, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),


    -- Classic Black Belt (ID = 5)
    (5, 36, 'main',
     'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/78ebce5408c94e9abcf484febd66898f_9366/Players_Reversible_Belt_Brown_JJ3852_01_00_standard.jpg'),
    (5, 37, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/7e2a8c4a9e854680bdd5dbecb14a8d36_9366/Players_Reversible_Belt_Brown_JJ3852_41_detail_hover.jpg'),
    (5, 38, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5bf861aca4ae494ca1924154d26d1c5b_9366/Players_Reversible_Belt_Brown_JJ3852_42_detail.jpg'),
    (5, 39, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5bf861aca4ae494ca1924154d26d1c5b_9366/Players_Reversible_Belt_Brown_JJ3852_42_detail.jpg'),
    (5, 40, 'extra',
     'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5bf861aca4ae494ca1924154d26d1c5b_9366/Players_Reversible_Belt_Brown_JJ3852_42_detail.jpg');

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