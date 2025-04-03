
INSERT INTO users (name, email, dateOfBirth, password, image, isDeleted, gender, phoneNumber, role, token, isActive)
VALUES
    ('John Doe', 'john.doe@example.com', '1990-05-15', '202cb962ac59075b964b07152d234b70', '/images/users/john.jpg', 0, 'Male', 1234567890, 1, NULL, 1),
    ('Jane Smith', 'jane.smith@example.com', '1995-08-22', '202cb962ac59075b964b07152d234b70', '/images/users/jane.jpg', 0, 'Female', 9876543210, 2, NULL, 1),
    ('Alice Brown', 'alice.brown@example.com', '1988-12-10', '202cb962ac59075b964b07152d234b70', NULL, 0, 'Female', 1122334455, 1, NULL, 1),
    ('Bob Johnson', 'bob.johnson@example.com', '1992-07-03', '202cb962ac59075b964b07152d234b70', '/images/users/bob.jpg', 0, 'Male', 5566778899, 2, NULL, 1),
    ('Charlie Davis', 'charlie.davis@example.com', '2000-01-30', '202cb962ac59075b964b07152d234b70', NULL, 0, 'Non-binary', 9988776655, 3, NULL, 1);

INSERT INTO paymentMethods (name, isActive)
VALUES
    ('GooglePay', 1),
    ('Delivery', 1),
    ('Momo', 1);

INSERT INTO belts (name, description, price, gender, releaseDate, isDeleted, discountRate, materialBelt)
VALUES
    ('Classic Leather Belt', 'A premium leather belt for formal wear.', 29.99, 'Unisex', '2024-03-01', 0, 10.0, 'Genuine Leather'),
    ('Casual Canvas Belt', 'A comfortable canvas belt for everyday use.', 19.99, 'Male', '2024-02-15', 0, 5.0, 'Canvas'),
    ('Luxury Gold Belt', 'An elegant gold-plated belt for special occasions.', 49.99, 'Female', '2024-01-10', 0, 15.0, 'Metal & Leather'),
    ('Sports Elastic Belt', 'A flexible and durable belt for active lifestyles.', 14.99, 'Unisex', '2024-03-20', 0, 0.0, 'Elastic'),
    ('Classic Black Belt', 'A stylish black belt suitable for all outfits.', 25.99, 'Male', '2024-02-05', 0, 8.0, 'Synthetic Leather');

INSERT INTO beltVariants (beltId, color, size, stockQuantity)
VALUES
    (1, 'Black', 'M', 50),
    (1, 'Brown', 'L', 40),
    (2, 'Beige', 'M', 30),
    (2, 'Green', 'L', 25),
    (3, 'Gold', 'S', 20),
    (3, 'Gold', 'M', 15),
    (4, 'Blue', 'L', 35),
    (4, 'Red', 'M', 40),
    (5, 'Black', 'S', 60),
    (5, 'Black', 'M', 55);

INSERT INTO imageEntry (beltId, variantId, imageType, imagePath)
VALUES
    (1, 1, 'main', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3b7f903209fb41a681f0a588bf331876_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM1.jpg'),
    (1, 1, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ad01fc33e59144279b93f0d890dc622e_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM3_hover.jpg'),
    (1, 2, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/b425b5ce702f4ebc8f2425105efef088_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM4.jpg'),
    (1, 2, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/9621155a1d4c47a3a0ed407e135d1902_9366/Originals_Golf_Leather_Belt_Green_JJ3844_HM5.jpg'),
    (2, 3, 'main', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/805475e310b9490ca12fb43b58f694b6_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM1.jpg'),
    (2, 3, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/812e9344d3d24e519a02ff72bb3aff0b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM3_hover.jpg'),
    (2, 4, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5925aa89992541fe9db4cdd72bbea33d_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM4.jpg'),
    (2, 4, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/67281d6b30cb4162bbb4ed7bc1ffd15b_9366/Originals_Golf_Leather_Belt_Blue_JD5724_HM5.jpg'),
    (3, 5, 'main', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f1c60ec31ec145bfa6879649511a6d98_9366/Reversible_Webbing_Belt_Grey_IQ2909_01_standard.jpg'),
    (3, 5, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/ff743cbdd53c465282facf2c9f431f7d_9366/Reversible_Webbing_Belt_Grey_IQ2909_41_detail_hover.jpg'),
    (3, 6, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/7fbdb09684bf442db91b07605ac5edd9_9366/Reversible_Webbing_Belt_Grey_IQ2909_42_detail.jpg'),
    (4, 6, 'main', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/f09f0a1d15a340288f57d28cfe08ba5f_9366/Reversible_Webbing_Belt_Blue_IY4418_01_standard.jpg'),
    (4, 7, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/94b43d1c2f1b4852bd3272d7756ac9ec_9366/Reversible_Webbing_Belt_Blue_IY4418_41_detail_hover.jpg'),
    (4, 7, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/2e62e0d0b92c4508bb3332693eefd9b8_9366/Reversible_Webbing_Belt_Blue_IY4418_42_detail.jpg'),
    (5, 8, 'main', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/78ebce5408c94e9abcf484febd66898f_9366/Players_Reversible_Belt_Brown_JJ3852_01_00_standard.jpg'),
    (5, 8, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/7e2a8c4a9e854680bdd5dbecb14a8d36_9366/Players_Reversible_Belt_Brown_JJ3852_41_detail_hover.jpg'),
    (5, 9, 'extra', 'https://assets.adidas.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/5bf861aca4ae494ca1924154d26d1c5b_9366/Players_Reversible_Belt_Brown_JJ3852_42_detail.jpg');