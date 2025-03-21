CREATE
DATABASE thatlungdb;


use
thatlungdb;

DROP
database thatlungdb;

-- 1. Bảng chính (không phụ thuộc bảng nào khác)
CREATE TABLE users
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)        NOT NULL,
    email       VARCHAR(255) UNIQUE NOT NULL,
    dateOfBirth DATETIME,
    password    VARCHAR(255)        NOT NULL,
    image       VARCHAR(255),
    createdAt   DATETIME DEFAULT CURRENT_TIMESTAMP,
    isDeleted   INT      DEFAULT 0,
    gender      VARCHAR(50),
    phoneNumber BIGINT,
    role        INT,
    token       VARCHAR(255),
    isActive    INT      DEFAULT 1
);

CREATE TABLE paymentMethods
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    isActive INT DEFAULT 1
);

CREATE TABLE addresses
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    userId        INT          NOT NULL,
    addressCity   VARCHAR(255) NOT NULL,
    addressStreet VARCHAR(500),
    isUse         INT DEFAULT 0,
    isDeleted     INT DEFAULT 0,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE belts
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  TEXT,
    price DOUBLE NOT NULL,
    gender       VARCHAR(50)  NOT NULL,
    releaseDate  DATE,
    createdAt    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    isDeleted    INT      DEFAULT 0,
    discountRate DOUBLE DEFAULT 0.0,
    materialBelt VARCHAR(255)
);

CREATE TABLE beltVariants
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    beltId        INT          NOT NULL,
    color         VARCHAR(100) NOT NULL,
    size          VARCHAR(50)  NOT NULL,
    stockQuantity INT      DEFAULT 0,
    createdAt     DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE
);

CREATE TABLE categories
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE beltCategory
(
    beltId     INT NOT NULL,
    variantId  INT NOT NULL,
    categoryId INT NOT NULL,
    PRIMARY KEY (beltId, categoryId, variantId),
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE,
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE,
    FOREIGN KEY (categoryId) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    userID          INT         NOT NULL,
    paymentMethodId INT,
    addressesId     INT,
    orderDate       DATETIME DEFAULT CURRENT_TIMESTAMP,
    orderTotal DOUBLE NOT NULL,
    orderStatus     VARCHAR(50) NOT NULL,
    isDeleted       INT      DEFAULT 0,
    FOREIGN KEY (userID) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (paymentMethodId) REFERENCES paymentMethods (id) ON DELETE CASCADE,
    FOREIGN KEY (addressesId) REFERENCES addresses (id) ON DELETE CASCADE
);

CREATE TABLE orderDetails
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    orderId   INT NOT NULL,
    price DOUBLE NOT NULL,
    variantId INT NOT NULL,
    beltId    INT NOT NULL,
    quantity  INT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE,
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE
);

CREATE TABLE reviews
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    beltId     INT NOT NULL,
    userId     INT NOT NULL,
    content    VARCHAR(1000),
    ratingStar INT CHECK (ratingStar BETWEEN 1 AND 5),
    createdAt  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE imageEntry
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    beltId    INT NOT NULL,
    variantId INT NOT NULL,
    imageType VARCHAR(50),
    imagePath VARCHAR(255),
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE
);

CREATE TABLE favorites
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    beltId    INT NOT NULL,
    variantId INT NOT NULL,
    userId    INT NOT NULL,
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE beltfavorites
(
    beltId     INT NOT NULL,
    variantId  INT NOT NULL,
    favoriteId INT NOT NULL,
    PRIMARY KEY (beltId, favoriteId, variantId),
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE,
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE,
    FOREIGN KEY (favoriteId) REFERENCES favorites (id) ON DELETE CASCADE
);

CREATE TABLE userPaymentMethods
(
    userId          INT NOT NULL,
    paymentMethodId INT NOT NULL,
    isUse           INT NOT NULL,
    PRIMARY KEY (userId, paymentMethodId),
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (paymentMethodId) REFERENCES paymentMethods (id) ON DELETE CASCADE
);

CREATE TABLE coupons
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    code      VARCHAR(50) NOT NULL,
    discountPercent DOUBLE DEFAULT 0.0,
    startDate DATETIME,
    endDate   DATETIME,
    isActive  INT DEFAULT 1
);

CREATE TABLE couponUsage
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    couponId INT NOT NULL,
    userId   INT NOT NULL,
    orderId  INT NOT NULL,
    usedAt   DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (couponId) REFERENCES coupons (id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE TABLE beltViews
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    beltId    INT      NOT NULL,
    viewDate  DATETIME NOT NULL,
    viewCount INT DEFAULT 1,
    CONSTRAINT unique_belt_view UNIQUE (beltId, viewDate),
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE
);

CREATE TABLE collectionDetails
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    createdAt   DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE collections
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    collectionDetailId INT          NOT NULL,
    FOREIGN KEY (collectionDetailId) REFERENCES collectionDetails (id) ON DELETE CASCADE
);

CREATE TABLE sessions
(
    sessionId VARCHAR(255) PRIMARY KEY,
    userId    INT NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE tokens
(
    tokenId   VARCHAR(255) PRIMARY KEY,
    userId    INT      NOT NULL,
    expiresAt DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE usersUsage
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    userEmail       VARCHAR(255) DEFAULT NULL,
    lastLogin       DATETIME     DEFAULT NULL,
    lastActivity    DATETIME     DEFAULT NULL,
    ipAddress       VARCHAR(45)  DEFAULT NULL,
    lastActiveRoute VARCHAR(255) DEFAULT NULL
);
