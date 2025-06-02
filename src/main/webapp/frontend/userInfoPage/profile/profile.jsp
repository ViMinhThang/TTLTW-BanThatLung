<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Thomas - Hồ sơ</title>
    <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/x-icon"
    />
    <jsp:include page="/frontend/components/userInfo/cdnLink/userInfoLink.jsp"/>

    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/profile.css"/>
</head>
<body>
<f:setLocale value="${sessionScope.lang}" scope="session" />
<f:setBundle basename="messages" />

<jsp:include page="/frontend/header_footer/header.jsp"/>
<header id="header"></header>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp"><f:message key="breadcrumb.home"/></a>
            </li>
            <li class="breadcrumb-item active" aria-current="page"><f:message key="breadcrumb.profile"/></li>
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
                <p class="fs-4"><f:message key="profile.avatar"/></p>
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
                <p class="fs-4"><f:message key="profile.avatar_requirements"/></p>
            </div>
            <hr/>
            <div class="d-flex mt-3">
                <p class="fs-4"><f:message key="profile.your_name"/>: </p>
                <p class="fs-4"> ${sessionScope.auth.name}</p>
            </div>
            <div class="d-flex mt-3">
                <p class="font-weight-bold fs-4"><f:message key="profile.gender"/>:</p>
                <div class="d-flex justify-content-center align-items-center ms-4">
                    <c:choose>
                        <c:when test="${sessionScope.auth.gender == 'M'}">
                            <input class="mb-3 ms-3" type="radio" name="gender" value="M" checked/>
                            <p class="mb-3 ms-2 fs-4"><f:message key="profile.male"/></p>
                            <input class="mb-3 ms-2" type="radio" name="gender" value="F"/>
                            <p class="mb-3 ms-2 fs-4"><f:message key="profile.female"/></p>
                        </c:when>
                        <c:otherwise>
                            <input class="mb-3 ms-3" type="radio" name="gender" value="M"/>
                            <p class="mb-3 ms-2 fs-4"><f:message key="profile.male"/></p>
                            <input class="mb-3 ms-2" type="radio" name="gender" value="F" checked/>
                            <p class="mb-3 ms-2 fs-4"><f:message key="profile.female"/></p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="d-flex  justify-content-start align-items-center mt-3">
                <p class="font-weight-bold fs-4"><f:message key="profile.birth_date"/>:</p>
                <div class="d-flex justify-content-center align-items-center fs-4">
                    <input
                            value="${sessionScope.auth.dateOfBirth}"
                            class="mb-3 ms-4"
                            type="date"
                            name="birthDate"
                            placeholder="<f:message key='profile.birth_date'/>"

                    />
                </div>
            </div>
            <div class="d-flex mt-3 justify-content-start align-items-center">
                <p class="font-weight-bold fs-4"><f:message key="profile.phone_number"/>:</p>
                <div class="d-flex justify-content-center align-items-center">
                    <input
                            value="${sessionScope.auth.phoneNumber}"
                            class="mb-3 ms-4"
                            type="text"
                            name="phoneNumber"
                            placeholder="<f:message key='profile.phone_number'/>"

                    />
                </div>
            </div>
        </div>
        <div class="d-flex mt-3 mb-5">
            <button type="submit" class="btn btn-dark px-4 py-2 custom__btn fs-4"><f:message key="profile.save"/></button>
        </div>
    </form>

</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>