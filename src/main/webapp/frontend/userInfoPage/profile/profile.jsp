<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Thomas - Cá nhân</title>
    <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/x-icon"
    />
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
    <script src="../../header footer/header__footer.js"></script>
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<header id="header"></header>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Hồ sơ</li>
        </ol>
    </nav>
</div>
<div class="container my-3">
    <div class="row">
        <h2 class="mt-5 mb-3">Tài khoản của tôi</h2>
        <div
                class="d-flex col-12"
                style="white-space: nowrap;"
        >
            <div class="d-inline-block fw-bold mb-4" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userInfo"
                >Tài khoản</a
                >
            </div>
            <div class="d-inline-block fw-bold custom_active" style="padding: 12px">
                <a
                        class="text-white custom_size--19"
                        href="${pageContext.request.contextPath}/userProfile"
                >Hồ sơ</a
                >
            </div>
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userAddress?userId=${sessionScope.auth.id}"
                >Địa chỉ</a
                >
            </div>
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userPrivacy"
                >Riêng tư</a
                >
            </div>
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/viewOrders"
                >Đơn hàng</a
                >
            </div>
        </div>

    </div>
    <form action="${pageContext.request.contextPath}/userProfile" method="post" enctype="multipart/form-data">
        <input type="hidden" name="message" value="update"/>
        <input type="hidden" name="userId" value="${sessionScope.auth.id}"/>
        <input type="hidden" name="userName" value="${sessionScope.auth.name}"/>
        <div class="container container-sm border rounded">
            <div class="d-flex mt-3">
                <p>Ảnh đại diện</p>
                <input type="file" name="uploadProfileImage" class="ms-3 uploadImage"/>
            </div>
            <div class="d-flex flex-column border-bottom">
                <img
                        src="${sessionScope.auth.image == null ? "/assets/images/sampleProfile1.jpg" : sessionScope.auth.image}"
                        alt="profile"
                        class="rounded-circle"
                        width="100"
                        height="100"
                />
                <p>Phải là file .png .jpg hoặc .jpeg và 400x400</p>
            </div>
            <div class="d-flex mt-3">
                <p>Tên của bạn:</p>
                <p class="ms-4">${sessionScope.auth.name}</p>
            </div>
            <div class="d-flex mt-3 border-bottom">
                <p class="font-weight-bold">Giới tính:</p>
                <div class="d-flex justify-content-center align-items-center ms-4">
                    <c:choose>
                        <c:when test="${sessionScope.auth.gender == 'M'}">
                            <input class="mb-3 ms-3" type="radio" name="gender" value="M" checked/>
                            <p class="mb-3 ms-2">Nam</p>
                            <input class="mb-3 ms-2" type="radio" name="gender" value="F"/>
                            <p class="mb-3 ms-2">Nữ</p>
                        </c:when>
                        <c:otherwise>
                            <input class="mb-3 ms-3" type="radio" name="gender" value="M"/>
                            <p class="mb-3 ms-2">Nam</p>
                            <input class="mb-3 ms-2" type="radio" name="gender" value="F" checked/>
                            <p class="mb-3 ms-2">Nữ</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="d-flex mt-3">
                <p class="font-weight-bold mr-5">Ngày sinh:</p>
                <div class="d-flex justify-content-center align-items-center ml-3">
                    <input
                            value="${sessionScope.auth.dateOfBirth}"
                            class="mb-3 ms-4"
                            type="date"
                            name="birthDate"
                            placeholder="birthDate"

                    />
                </div>
            </div>
            <div class="d-flex mt-3">
                <p class="font-weight-bold mr-5">Số điện thoại:</p>
                <div class="d-flex justify-content-center align-items-center ml-3">
                    <input
                            value="${sessionScope.auth.phoneNumber}"
                            class="mb-3 ms-4"
                            type="text"
                            name="phoneNumber"
                            placeholder="Số điện thoại"

                    />
                </div>
            </div>
        </div>
        <div class="d-flex mt-3 mb-5 ml-2">
            <button type="submit" class="btn btn-dark px-4 py-2 custom__btn">Lưu</button>
        </div>
    </form>

</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>