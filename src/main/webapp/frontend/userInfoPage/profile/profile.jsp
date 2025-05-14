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
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfo.css"/>
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
    <jsp:include page="/frontend/components/userInfo/navigationBar/navBar.jsp"/>
    <form action="${pageContext.request.contextPath}/userProfile" method="post" enctype="multipart/form-data">
        <input type="hidden" name="message" value="update"/>
        <input type="hidden" name="userId" value="${sessionScope.auth.id}"/>
        <input type="hidden" name="userName" value="${sessionScope.auth.name}"/>
        <div class="container container-sm ps-0">
            <div class="d-flex mt-3">
                <p class="fs-4">Ảnh đại diện</p>
                <input type="file" name="uploadProfileImage" class="ms-3 uploadImage"/>
            </div>
            <div class="d-flex flex-column">
                <img
                        src="${sessionScope.auth.image == null ? "/assets/images/sampleProfile1.jpg" : sessionScope.auth.image}"
                        alt="profile"
                        class="rounded-circle"
                        width="100"
                        height="100"
                />
                <p class="fs-4">Phải là file .png .jpg hoặc .jpeg và 400x400</p>
            </div>
            <hr/>
            <div class="d-flex mt-3">
                <p class="fs-4">Tên của bạn: </p>
                <p class="fs-4"> ${sessionScope.auth.name}</p>
            </div>
            <div class="d-flex mt-3">
                <p class="font-weight-bold fs-4">Giới tính:</p>
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
                            <p class="mb-3 ms-2 fs-4">Nam</p>
                            <input class="mb-3 ms-2" type="radio" name="gender" value="F" checked/>
                            <p class="mb-3 ms-2 fs-4">Nữ</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="d-flex  justify-content-start align-items-center mt-3">
                <p class="font-weight-bold fs-4">Ngày sinh:</p>
                <div class="d-flex justify-content-center align-items-center fs-4">
                    <input
                            value="${sessionScope.auth.dateOfBirth}"
                            class="mb-3 ms-4"
                            type="date"
                            name="birthDate"
                            placeholder="birthDate"

                    />
                </div>
            </div>
            <div class="d-flex mt-3 justify-content-start align-items-center">
                <p class="font-weight-bold fs-4">Số điện thoại:</p>
                <div class="d-flex justify-content-center align-items-center">
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
        <div class="d-flex mt-3 mb-5">
            <button type="submit" class="btn btn-dark px-4 py-2 custom__btn fs-4">Lưu</button>
        </div>
    </form>

</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>