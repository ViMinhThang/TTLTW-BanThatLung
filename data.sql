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
VALUES ('Golf Braided Stretch Belt', 'Male', '2024-01-10', 10.0, 'Canvas'),
       ('Golf Orange Stretch Belt', 'Female', '2024-02-15', 15.0, 'Da'),
       ('3-Stripes Tour Belt', 'Male', '2024-03-05', 20.0, 'Canvas'),
       ('Originals Golf Leather Belt', 'Unisex', '2024-01-25', 5.0, 'Da'),
       ('Reversible Webbing Belt', 'Female', '2024-04-01', 20.0, 'Canvas');


INSERT INTO beltVariants (beltId, sizeId, colorId, description, price)
VALUES (1, 1, 1,
        'A stretchy golf belt that completes your on-course look.No golf outfit is complete without a belt. This one from adidas ups your style game with braided stretch material that looks as good as it feels. A metal pin buckle adds the finishing touch.',
        250000),
       (1, 1, 2, 'Classic style with polished buckle', 260000),
       (2, 2, 2, 'Slim belt for dresses and skirts', 220000),
       (2, 2, 1, 'Slim belt for dresses and skirts', 260000),
       (3, 1, 3, 'Brown vintage look', 270000),
       (3, 1, 1, 'Brown vintage look', 270000),
       (4, 3, 4, 'Wide modern belt for streetwear', 300000),
       (4, 3, 1, 'Wide modern belt for streetwear', 300000),
       (5, 2, 5, 'Punk-inspired look with studs', 320000),
       (5, 4, 5, 'Punk-inspired look with studs', 320000);


INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES (1, 1, 'main',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3762e762e4d44f46a385af2b0006f48f_9366/Golf_Braided_Stretch_Belt_Blue_HS5558_01_standard.jpg'),
       (1, 1, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/298948ccfd864e7fbd21af2b00072d1f_9366/Golf_Braided_Stretch_Belt_Blue_HS5558_41_detail_hover_hover_hover_hover.jpg'),
       (1, 1, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/96f047c819e84c209634af2b00079003_9366/Golf_Braided_Stretch_Belt_Blue_HS5558_42_detail.jpg'),
       (1, 2, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f09f0a1d15a340288f57d28cfe08ba5f_9366/Reversible_Webbing_Belt_Blue_IY4418_01_standard.jpg'),
       (1, 2, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/94b43d1c2f1b4852bd3272d7756ac9ec_9366/Reversible_Webbing_Belt_Blue_IY4418_41_detail_hover.jpg'),
       (1, 2, 'sub',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg');

INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES (2, 3, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3176960c668d425eb6b50734ca01d46d_9366/Braided_Stretch_Belt_Green_JI6581_01_00_standard.jpg'),
       (2, 3, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/73718728bc314a48820338cf1d6e05cd_9366/Braided_Stretch_Belt_Green_JI6581_41_detail_hover.jpg'),
       (2, 3, 'sub',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/0a4a90de4a5442abbc44278f0613cb52_9366/Braided_Stretch_Belt_Green_JI6581_42_detail.jpg'),
       (2, 4, 'main',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/b27a233bd2b64983ad04af2b0004648f_9366/Golf_Braided_Stretch_Belt_Beige_HS5559_01_standard.jpg'),
       (2, 4, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/d50131bc6e3c4944af69af2b00049ab8_9366/Golf_Braided_Stretch_Belt_Beige_HS5559_41_detail_hover_hover_hover_hover.jpg'),
       (2, 4, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/0325bac2c3f34cfa9de7af2b0005007c_9366/Golf_Braided_Stretch_Belt_Beige_HS5559_42_detail.jpg');

INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES (3, 5, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f4daa0b1ecfb46439ce723c9a514f2f7_9366/3-Stripes_Tour_Belt_Blue_JD7097_01_00_standard.jpg'),
       (3, 5, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5cb8fc00b5fd418ba86472d32438c422_9366/3-Stripes_Tour_Belt_Blue_JD7097_41_detail_hover.jpg'),
       (3, 5, 'sub',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/b7c4db76b54e43fc8438bca89ef7e2ed_9366/3-Stripes_Tour_Belt_Blue_JD7097_42_detail.jpg'),
       (3, 6, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/1e2d6ab5d120415bb0d80db4c6c7b40f_9366/3-Stripes_Tour_Belt_Black_JJ3847_01_00_standard.jpg'),
       (3, 6, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/a4c91a48052642edb6d583625df9a8ee_9366/3-Stripes_Tour_Belt_Black_JJ3847_41_detail_hover.jpg'),
       (3, 6, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/fe6e8ba3054a49298dbba16677691b02_9366/3-Stripes_Tour_Belt_Black_JJ3847_42_detail.jpg');

INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES (4, 7, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/805475e310b9490ca12fb43b58f694b6_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM1.jpg'),
       (4, 7, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/812e9344d3d24e519a02ff72bb3aff0b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM3_hover.jpg'),
       (4, 7, 'sub',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/5925aa89992541fe9db4cdd72bbea33d_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM4.jpg'),
       (4, 8, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3b7f903209fb41a681f0a588bf331876_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM1.jpg'),
       (4, 8, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ad01fc33e59144279b93f0d890dc622e_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM3_hover.jpg'),
       (4, 8, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/b425b5ce702f4ebc8f2425105efef088_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM4.jpg');

INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES (5, 9, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f1c60ec31ec145bfa6879649511a6d98_9366/Reversible_Webbing_Belt_Grey_IQ2909_01_standard.jpg'),
       (5, 9, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ff743cbdd53c465282facf2c9f431f7d_9366/Reversible_Webbing_Belt_Grey_IQ2909_41_detail_hover.jpg'),
       (5, 9, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/7fbdb09684bf442db91b07605ac5edd9_9366/Reversible_Webbing_Belt_Grey_IQ2909_42_detail.jpg'),
       (5, 10, 'main',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/cf636fc9a4734c18bd8118190f3298e9_9366/Reversible_Webbing_Belt_Green_IY4419_01_standard.jpg'),
       (5, 10, 'sub',
        'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/73420ebb53004dd4bc50bdfd82f49bb3_9366/Reversible_Webbing_Belt_Green_IY4419_41_detail_hover.jpg'),
       (5, 10, 'sub',
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/a9f8d7ecd9ac4587b0fd9d139542e274_9366/Reversible_Webbing_Belt_Green_IY4419_42_detail.jpg');



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

INSERT INTO inventory(beltId, variantId, stockQuantity)
VALUES (1, 1, 50),
       (1, 2, 56),
       (2, 3, 120),
       (2, 4, 56),
       (3, 5, 23),
       (3, 6, 12),
       (4, 7, 23),
       (4, 8, 23),
       (5, 9, 23),
       (5, 10, 23);