CREATE
DATABASE thatlungdb;


use
thatlungdb;

DROP
database thatlungdb;
-- 1. Các bảng không có ràng buộc FOREIGN KEY trước
CREATE TABLE collectionDetails
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    createdAt   DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE paymentMethods
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    isActive INT
);

CREATE TABLE coupons
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    code      VARCHAR(50) NOT NULL,
    discountRate DOUBLE DEFAULT 0.0,
    startDate DATETIME,
    endDate   DATETIME,
    isActive  INT
);

CREATE TABLE Resources
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    resourceName VARCHAR(255) NOT NULL
);

CREATE TABLE Permissions
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    permissionName VARCHAR(255) NOT NULL
);

CREATE TABLE `Groups`
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    groupName VARCHAR(255) NOT NULL
);

CREATE TABLE categories
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE suppliers
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    contactInfo TEXT,
    createdAt   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Bảng users
CREATE TABLE users
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255)        NOT NULL,
    name        VARCHAR(255),
    dateOfBirth DATETIME,
    image       VARCHAR(255),
    gender      VARCHAR(50),
    phoneNumber BIGINT,
    role        INT,
    token       VARCHAR(255),
    isActive    INT,
    is_verified BOOLEAN   DEFAULT FALSE,
    isDeleted   INT       DEFAULT 0,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Bảng phụ liên quan đến users
CREATE TABLE usersUsage
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    userId          INT          DEFAULT NULL,
    lastLogin       DATETIME     DEFAULT NULL,
    lastActivity    DATETIME     DEFAULT NULL,
    ipAddress       VARCHAR(45)  DEFAULT NULL,
    lastActiveRoute VARCHAR(255) DEFAULT NULL,
    label           VARCHAR(255) DEFAULT NULL,
    Alert           VARCHAR(255) DEFAULT NULL,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
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

-- 4. Phân quyền
CREATE TABLE UserGroups
(
    userId  INT,
    groupId INT,
    PRIMARY KEY (userId, groupId),
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (groupId) REFERENCES `Groups` (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE GroupPermissions
(
    groupId      INT,
    resourceId   INT,
    permissionId INT,
    PRIMARY KEY (groupId, resourceId, permissionId),
    FOREIGN KEY (groupId) REFERENCES `Groups` (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (resourceId) REFERENCES Resources (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (permissionId) REFERENCES Permissions (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 5. Bảng sản phẩm (belts) và các bảng liên quan
CREATE TABLE belts
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   TEXT,
    price DOUBLE NOT NULL,
    gender        VARCHAR(50)  NOT NULL,
    stockQuantity INT      DEFAULT 0,
    releaseDate   DATE,
    createdAt     DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    isDeleted     INT      DEFAULT 0,
    discountRate DOUBLE DEFAULT 0.0,
    materialBelt  VARCHAR(255)
);

CREATE TABLE beltVariants
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    beltId        INT         NOT NULL,
    size          VARCHAR(50) NOT NULL,
    color         VARCHAR(50),
    stockQuantity INT DEFAULT 0,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE
);

CREATE TABLE beltCategory
(
    beltId     INT NOT NULL,
    categoryId INT NOT NULL,
    PRIMARY KEY (beltId, categoryId),
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (categoryId) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE imageEntry
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    beltId    INT NOT NULL,
    variantId INT NOT NULL,
    imageType VARCHAR(50),
    imagePath VARCHAR(255),
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reviews
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    beltId     INT NOT NULL,
    userId     INT NOT NULL,
    content    VARCHAR(1000),
    ratingStar INT CHECK (ratingStar BETWEEN 1 AND 5),
    createdAt  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE favorites
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    beltId INT NOT NULL,
    userId INT NOT NULL,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE beltfavorites
(
    beltId     INT NOT NULL,
    favoriteId INT NOT NULL,
    PRIMARY KEY (beltId, favoriteId),
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (favoriteId) REFERENCES favorites (id) ON DELETE CASCADE ON UPDATE CASCADE
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

-- 6. Bộ sưu tập
CREATE TABLE collections
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    collectionDetailId INT          NOT NULL,
    FOREIGN KEY (collectionDetailId) REFERENCES collectionDetails (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 7. Đơn hàng & thanh toán
CREATE TABLE addresses
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    userId      INT NOT NULL,
    addressLine VARCHAR(255),
    city        VARCHAR(100),
    country     VARCHAR(100),
    createdAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
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
    FOREIGN KEY (paymentMethodId) REFERENCES paymentMethods (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userID) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (addressesId) REFERENCES addresses (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE orderDetails
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    orderId   INT NOT NULL,
    variantId INT NOT NULL,
    price DOUBLE NOT NULL,
    beltId    INT NOT NULL,
    quantity  INT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (variantId) REFERENCES beltVariants (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (beltId) REFERENCES belts (id) ON DELETE CASCADE ON UPDATE CASCADE
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
CREATE TABLE stockTransactions
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    beltId          INT         NOT NULL,
    beltVariantId   INT         NOT NULL,
    transactionType VARCHAR(50) NOT NULL,
    quantity        INT         NOT NULL,
    transactionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    createdAt       DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt       DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (beltVariantId) REFERENCES beltVariants (id) ON DELETE CASCADE
);

CREATE TABLE purchases
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    supplierId    INT NOT NULL,
    beltId        INT NOT NULL,
    beltVariantId INT NOT NULL,
    quantity      INT NOT NULL,
    purchaseDate  DATETIME DEFAULT CURRENT_TIMESTAMP,
    createdAt     DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (beltId) REFERENCES belts (id),
    FOREIGN KEY (supplierId) REFERENCES suppliers (id),
    FOREIGN KEY (beltVariantId) REFERENCES beltVariants (id) ON DELETE CASCADE
);
