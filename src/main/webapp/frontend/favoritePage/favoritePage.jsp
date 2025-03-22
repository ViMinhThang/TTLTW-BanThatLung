<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Yêu thích</title>
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
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <script src="${pageContext.request.contextPath}/js/favoritePage.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/favoritePage.css"/>
</head>

<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div id="dimmer2" class="dimmer2"></div>
<header id="header"></header>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Yêu thích</li>
        </ol>
    </nav>
</div>
<h1 class="text-center" style="margin-top: 208px">Mục yêu thích của bạn</h1>
<div class="wishlist-container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col" class="text-start fw-light">Tên sản phẩm</th>
            <th scope="col" class="text-center fw-light">Giá</th>
            <th scope="col" class="text-center fw-light">Tình trạng</th>
            <th scope="col" class="text-center fw-light"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="belt" items="${favoriteBelts}">
            <c:forEach var="variant" items="${belt.beltVariants}">
                <tr class="fs-5 fw-light tr-favorite">
                    <input class="beltId" type="hidden" name="beltId" value="${belt.id}">
                    <input class="variantId" type="hidden" name="variantId" value="${variant.id}">
                    <input class="userId" type="hidden" name="userId" value="${sessionScope.auth.id}">

                    <td>
                        <span class="remove-btn">×</span>
                    </td>
                    <td class="text-start d-flex align-items-center">
                        <!-- Ảnh đầu tiên của variant -->
                        <c:if test="${not empty variant.images}">
                            <img src="${pageContext.request.contextPath}${variant.images[0]}"
                                 alt="Product Image"
                                 class="belts-img img-fluid me-3" style="width: 80px; height: 80px; object-fit: cover;">
                        </c:if>
                        <div>
                            <p class="mb-0">${belt.name} - ${variant.color} (${variant.size})</p>
                        </div>
                    </td>
                    <td class="text-center">${belt.price} vnđ</td>
                    <td class="text-center">${variant.stockQuantity != 0 ? "Còn hàng" : "Hết hàng"}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
    <div class="wishlist-footer d-flex justify-content-end" style="margin-right: 30px;">
        <a href="/Cart" class="btn btn-dark text-white">Xem giỏ hàng</a>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
