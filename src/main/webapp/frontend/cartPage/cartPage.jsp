<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <f:setLocale value="${sessionScope.lang}" scope="session" />
    <f:setBundle basename="messages" />
    <title><f:message key="cart.title"/></title>
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
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
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
                <a href="${pageContext.request.contextPath}/index.jsp"><f:message key="favorite.home"/></a>
            </li>
            <li class="breadcrumb-item active" aria-current="page"><f:message key="cart.title"/></li>
        </ol>
    </nav>
</div>
<div class="custom_margin--container" style="margin-top: 150px">
    <div class="row">
        <div class="col-6 mb-5 pe-5" style="margin-left: 196.25px">
            <div class="row">
                <h1 class="ps-0"><f:message key="cart.your_cart"/></h1>
                <h3 class="ps-0 fw-light fs-5 totalOrdersCountDisplay">
                    <f:message key="cart.total_orders">
                        <f:param value="${cartItemList.size()}"/>
                    </f:message>
                </h3>
            </div>
            <div class="cart-list-wrapper">
                <c:forEach var="cartItem" items="${cartItemList}">
                    <div class="cart-item d-flex border border-dark mb-3">
                        <input value="${cartItem.belt.id}" type="hidden" class="beltId">
                        <input value="${cartItem.variant.id}" type="hidden" class="variantId">
                        <!-- Ảnh sản phẩm -->
                        <div class="me-3">
                            <img src="${pageContext.request.contextPath}${cartItem.variant.images[0]}"
                                 class="img-fluid shadow-sm"
                                 alt="${cartItem.belt.name}"
                                 style="width: 200px; height: 200px; object-fit: cover;">
                        </div>

                        <div class="d-flex flex-column flex-grow-1 p-2">
                            <div class="d-flex justify-content-between align-items-center">
                                <p class="fw-bold fs-5 beltName mb-1">${cartItem.belt.name}</p>
                                <p class="fw-bold fs-5">${cartItem.price} <f:message key="cart.currency"/></p>
                                <div class="asd">
                                    <p class="remove_button fs-5">&times;</p>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                                    ${cartItem.variant.color}, ${cartItem.variant.size}
                            </div>

                            <div class="d-flex mt-2">
                                <c:forEach var="category" items="${cartItem.variant.categoryList}">
                                    <span class="badge bg-secondary me-2">${category.name}</span>
                                </c:forEach>
                            </div>

                            <div class="mt-2">
                                <label for="quantity-${cartItem.variant.id}" class="fw-bold"><f:message key="cart.quantity"/>:</label>
                                <select id="quantity-${cartItem.variant.id}"
                                        class="form-select option_select quantitySelectCart"
                                        onchange="updateCart(${cartItem.variant.id}, this.value)"
                                    ${cartItem.variant.stockQuantity == 0 ? 'disabled' : ''}>
                                    <c:forEach var="i" begin="1" end="${cartItem.variant.stockQuantity}">
                                        <option value="${i}" ${i == cartItem.quantity ? 'selected' : ''}>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>
        <div class="col-3 mb-5 ps-5" style="width: 450px">
            <div class="row custom_insert">
                <c:choose>
                    <c:when test="${empty sessionScope.auth==null}">
                        <c:if test="${sessionScope.auth==null}">
                            <p class="ps-0"><f:message key="cart.login_required"/></p>
                        </c:if>
                        <a
                                href="#"
                                class="btn btn-dark px-3 py-2 fs-5 custom_button checkoutPage disabled fw-bold"
                                style="pointer-events: none; padding-top: 12px;
                                padding-bottom: 12px;"
                        ><f:message key="cart.proceed_checkout"/>
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
                        ><f:message key="cart.proceed_checkout"/>
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
                        <h5 class="fw-bold fs-3"><f:message key="cart.order_summary"/></h5>
                    </div>
                    <div class="d-flex justify-content-between mb-2">
                        <p class="mb-0 totalOrdersDisplayBelts fs-5">
                            <f:message key="cart.products_count">
                                <f:param value="${cartItemList.size()}"/>
                            </f:message>
                        </p>
                        <c:set var="totalPrice" value="0"/>
                        <c:forEach var="cartItem" items="${cartItemList}">
                            <c:set var="totalPrice" value="${totalPrice + cartItem.price}"/>
                        </c:forEach>
                        <p class="mb-0 totalPriceDisplay fs-5">${totalPrice} <f:message key="cart.currency"/></p>
                    </div>
                    <div class="pt-2">
                        <div class="d-flex justify-content-between fw-bold mb-2">
                            <p class="mb-0 fw-bold"><f:message key="cart.total"/></p>
                            <p class="mb-0 totalCostDisplay fs-5">
                                ${totalPrice} <f:message key="cart.currency"/>
                            </p>
                        </div>
                        <p class="text-muted small mb-0 fs-6"><f:message key="cart.tax_included"/></p>
                    </div>
                    <div
                            class="input-group custom_input_group custom_input--btn mt-4"
                    >
                        <input
                                type="text"
                                class="form-control custom_input--btn-group__input rounded-pill-start fs-5 couponValue"
                                placeholder="<f:message key="cart.coupon_placeholder"/>"
                        />
                        <button
                                class="btn btn-dark input-group-text custom_button_design custom_input--btn-group__btn rounded-pill-end"
                        >
                            <f:message key="cart.apply"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-14">
            <h2 class="my-5" style="margin-left: 190px"><f:message key="cart.suggestions"/></h2>
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
                            <t:beltCard belt="${belt}" height="234.25px" padding="p-0"/>
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