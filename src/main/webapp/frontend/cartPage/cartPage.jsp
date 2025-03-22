<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Giỏ hàng</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cartPage.js"></script>
    <script src="${pageContext.request.contextPath}/js/favorite.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/favorite.css"/>

    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartPage.css"/>
</head>

<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div id="dimmer2" class="dimmer2"></div>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Giỏ hàng</li>
        </ol>
    </nav>
</div>
<div class="custom_margin--container" style="margin-top: 250px">
    <div class="row">
        <div class="col-6 mb-5 pe-5" style="margin-left: 196.25px">
            <div class="row">
                <h1 class="ps-0">Giỏ hàng của bạn</h1>
                <h3 class="ps-0 fw-light fs-5 totalOrdersCountDisplay">
                    Tổng [${totalOrders} đơn hàng]
                </h3>
            </div>
            <c:forEach var="entry" items="${cart}">
                <c:set var="cartItem" value="${entry.value}"/>

                <div class="cart-item d-flex p-3 border-bottom">
                    <!-- Ảnh sản phẩm -->
                    <div class="me-3">
                        <img src="${pageContext.request.contextPath}${cartItem.variant.images[0]}"
                             class="img-fluid rounded shadow-sm"
                             alt="${cartItem.belt.name}"
                             style="width: 100px; height: 100px; object-fit: cover;">
                    </div>

                    <!-- Thông tin sản phẩm -->
                    <div class="d-flex flex-column flex-grow-1">
                        <div class="d-flex justify-content-between">
                            <p class="fw-bold">${cartItem.belt.name}
                                - ${cartItem.variant.color}, ${cartItem.variant.size}</p>
                            <p class="fw-bold">${cartItem.price} VNĐ</p>
                        </div>

                        <!-- Danh mục (Categories) của Variant -->
                        <div class="d-flex mt-2">
                            <c:forEach var="category" items="${cartItem.variant.categories}">
                                <span class="badge bg-secondary me-2">${category}</span>
                            </c:forEach>
                        </div>

                        <!-- Chọn số lượng -->
                        <div class="mt-2">
                            <label for="quantity-${cartItem.variant.id}" class="fw-bold">Số lượng:</label>
                            <select id="quantity-${cartItem.variant.id}"
                                    class="form-select w-25 d-inline-block"
                                    onchange="updateCart(${cartItem.variant.id}, this.value)">
                                <c:forEach var="i" begin="1" end="${cartItem.variant.stockQuantity}">
                                    <option value="${i}" ${i == cartItem.quantity ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="col-3 mb-5 ps-5" style="width: 450px">
            <div class="row custom_insert">
                <c:choose>
                    <c:when test="${empty sessionScope.cart || sessionScope.auth==null}">
                        <c:if test="${sessionScope.auth==null}">
                            <p class="ps-0">Vui lòng đăng nhập để thanh toán</p>
                        </c:if>
                        <a
                                href="#"
                                class="btn btn-dark px-3 py-2 fs-5 custom_button checkoutPage disabled fw-bold"
                                style="pointer-events: none; padding-top: 12px;
                                padding-bottom: 12px;"
                        >Tiến hành thanh toán
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    height="24px"
                                    viewBox="0 -960 960 960"
                                    width="24px"
                                    fill="#EFEFEF"
                            >
                                <path
                                        d="M647-440H160v-80h487L423-744l57-56 320 320-320 320-57-56 224-224Z"
                                />
                            </svg>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a
                                href="${pageContext.request.contextPath}/checkout"
                                class="btn btn-dark px-3 py-2 fs-5 custom_button checkoutPage fw-bold"
                                style="padding-top: 12px;padding-bottom: 12px;"
                        >Tiến hành thanh toán
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    height="24px"
                                    viewBox="0 -960 960 960"
                                    width="24px"
                                    fill="#EFEFEF"
                            >
                                <path
                                        d="M647-440H160v-80h487L423-744l57-56 320 320-320 320-57-56 224-224Z"
                                />
                            </svg>
                        </a>
                    </c:otherwise>
                </c:choose>

                <div class="mt-4 ps-0">
                    <div class="pb-2 mb-3">
                        <h5 class="fw-bold fs-3">Tóm tắt đơn hàng</h5>
                    </div>
                    <div class="d-flex justify-content-between mb-2">
                        <p class="mb-0 totalOrdersDisplayBelts fs-5">${cart.size()} sản phẩm</p>
                        <c:set var="totalPrice" value="0"/>
                        <c:forEach var="entry" items="${cart}">
                            <c:set var="totalPrice" value="${totalPrice + entry.value.price}"/>
                        </c:forEach>
                        <p class="mb-0 totalPriceDisplay fs-5">${totalPrice} VNĐ</p>
                    </div>
                    <div class="d-flex justify-content-between mb-2">
                        <p class="mb-0 fs-5">Vận chuyển</p>
                        <c:set var="totalOrders" value="15000"/>
                        <p class="mb-0 shipmentDisplay fs-5">${formattedShipmentPrice} VNĐ</p>
                    </div>
                    <div class="pt-2">
                        <div class="d-flex justify-content-between fw-bold mb-2">
                            <p class="mb-0 fw-bold">Tổng cộng</p>
                            <p class="mb-0 totalCostDisplay fs-5">
                                ${grandTotal} VNĐ
                            </p>
                        </div>
                        <p class="text-muted small mb-0 fs-6">(bao gồm cả thuế)</p>
                    </div>
                    <div
                            class="input-group custom_input_group custom_input--btn mt-4"
                    >
                        <input
                                type="text"
                                class="form-control custom_input--btn-group__input rounded-pill-start fs-5 couponValue"
                                placeholder="Nhập coupon"
                        />
                        <button
                                class="btn btn-dark input-group-text custom_button_design custom_input--btn-group__btn rounded-pill-end"
                        >
                            Áp dụng
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-14">
            <h2 class="my-5" style="margin-left: 190px">Gợi ý cho bạn</h2>
            <div
                    class="row mb-5"
                    id="list__product__row"
                    style="margin-left: 190px; margin-right: 190px"
            >
                <div class="list__product__element">
                    <div
                            class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4 justify-content-between"
                    >
                        <c:forEach var="belt" items="${suggestionBelts}">
                            <a href="productDetails?beltId=${belt.id}&variantId=${belt.beltVariants[0].id}"
                               class="text-decoration-none text-dark">
                                <div class="text-center hover--black">
                                    <!-- Hiển thị ảnh của biến thể đầu tiên -->
                                    <c:if test="${not empty belt.beltVariants}">
                                        <c:set var="firstVariant" value="${belt.beltVariants[0]}"/>
                                        <c:if test="${not empty firstVariant.images}">
                                            <img src="${pageContext.request.contextPath}${firstVariant.images[0]}"
                                                 class="img-fluid w-100 rounded shadow-sm"
                                                 alt="${belt.name}"
                                                 style="height: 25rem; object-fit: cover;">
                                        </c:if>
                                    </c:if>

                                    <!-- Thông tin sản phẩm -->
                                    <div class="mt-2 text-start ps-3">
                                        <p class="fw-bold fs-5 mb-1">${belt.price} VNĐ</p>
                                        <p class="text-muted mb-1">${belt.name}</p>
                                        <span class="badge bg-secondary">${belt.discountRate}%</span>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>
