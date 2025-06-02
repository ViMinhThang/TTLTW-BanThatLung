<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.thomas.constant.Iconstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%
    Locale locale = (Locale) session.getAttribute("lang");
    if (locale == null) {
        locale = request.getLocale();
        session.setAttribute("lang", locale);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Sign in</title>
    <link rel="icon" href="https://cdn-web-servlet.vercel.app/images/favicon.svg" type="image/x-icon"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/general.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/signInPage.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/footer.css"/>
    <!-- Thêm script reCAPTCHA v3 -->
    <script src="https://www.google.com/recaptcha/api.js?render=6Ld-QAgrAAAAAJTKPkDgBUW7_qoOrzAMiDOocwfR"></script>
    <script>
        grecaptcha.ready(function () {
            grecaptcha.execute('6Ld-QAgrAAAAAJTKPkDgBUW7_qoOrzAMiDOocwfR', {action: 'login'}).then(function (token) {
                document.getElementById('g-recaptcha-response').value = token;
            });
        });
    </script>
</head>

<body>
<f:setLocale value="${sessionScope.lang}" scope="session" />
<f:setBundle basename="messages" />

<div class="row p-2 border-bottom border-dark">
    <a
            href="${pageContext.request.contextPath}/index.jsp"
            class="d-sm-none d-md-none d-lg-block d-flex text-dark text-decoration-none fs-4 ms-2"
    >
        THOMAS / <f:message key="signin.title"/>
    </a>
</div>
<%-- khối hiển thị lỗi OAuth --%>
<div class="container-lg">
    <c:if test="${not empty param.error}">
        <div class="alert alert-dismissible fade show mt-4" role="alert">
            <c:choose>
                <c:when test="${param.error == 'email_exists'}">
                    <div class="alert alert-warning">
                        <i class="fas fa-exclamation-triangle"></i>
                        <strong><f:message key="signin.error_email_exists"/></strong><br>
                        <f:message key="signin.error_email_exists_desc"/>
                    </div>
                </c:when>
                <c:when test="${param.error == 'google_error'}">
                    <div class="alert alert-danger">
                        <i class="fab fa-google"></i>
                        <strong><f:message key="signin.error_google"/></strong><br>
                        <f:message key="signin.error_google_desc"/>
                    </div>
                </c:when>
                <c:when test="${param.error == 'facebook_error'}">
                    <div class="alert alert-danger">
                        <i class="fab fa-facebook"></i>
                        <strong><f:message key="signin.error_facebook"/></strong><br>
                        <f:message key="signin.error_facebook_desc"/>
                    </div>
                </c:when>
                <c:when test="${param.error == 'facebook_failed'}">
                    <div class="alert alert-danger">
                        <i class="fab fa-facebook"></i>
                        <strong><f:message key="signin.error_facebook_failed"/></strong><br>
                        <f:message key="signin.error_facebook_failed_desc"/>
                    </div>
                </c:when>

            </c:choose>

            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
</div>
<div class="container-lg d-flex justify-content-between">
    <form method="POST" class="col-7 mt-5" action="/login">
        <h2 class="custom_size--19 fw-light"><f:message key="signin.welcome_back"/></h2>
        <h2 class="custom_size--19 fw-light">
            <f:message key="signin.signin_with_email"/>
        </h2>
        <div class="ps-0">
            <p class="row justify-content-end text-danger"><f:message key="signin.required_info"/></p>
            <div class="d-flex flex-column mt-3">
                <p class="mb-1 custom_size--16"><f:message key="signin.email"/></p>
                <input
                        name="userEmail"
                        type="email"
                        class="form-control form-control-lg custom_design custom_size--16"
                        placeholder="<f:message key="signin.enter_email"/>"
                />
            </div>
            <div class="d-flex flex-column mt-3">
                <p class="mb-1 custom_size--16"><f:message key="signin.enter_password"/></p>
                <input
                        name="password"
                        type="password"
                        class="form-control form-control-lg custom_design custom_size--16"
                        placeholder="<f:message key="signin.enter_password"/>"
                />
                <c:if test="${not empty errorMessage}">
                    <p class="text-danger mt-2 custom_size--16 mb-0">${errorMessage}</p>
                </c:if>
            </div>
            <input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response">
            <div class="d-flex flex-column mt-3">
                <a
                        class="text-dark"
                        href="${pageContext.request.contextPath}/forgotPassword"
                ><f:message key="signin.forgot_password"/></a>
                <a
                        class="text-dark"
                        href="${pageContext.request.contextPath}/signup"
                ><f:message key="signin.register"/></a>
            </div>
            <div class="d-flex flex-column gap-4 mt-5">
                <!-- Nút Đăng nhập (không bo viền) -->
                <div class="d-flex justify-content-end">
                    <button type="submit"
                            class="btn-dark py-3 px-5 custom__btn custom-login-btn w-100 w-lg-50">
                        <f:message key="signin.signin_button"/>
                    </button>
                </div>

                <!-- Phần đăng nhập bằng Google (có viền) -->
                <div class="d-flex flex-column align-items-center gap-3 mt-4">
                    <!-- Divider with text -->
                    <div class="d-flex align-items-center w-100">
                        <hr class="flex-grow-1">
                        <span class="px-3 custom_size--14 text-muted"><f:message key="signin.or_signin_with"/></span>
                        <hr class="flex-grow-1">
                    </div>

                    <!-- Social login buttons - nằm ngang -->
                    <div class="d-flex justify-content-center gap-3">
                        <a href="https://accounts.google.com/o/oauth2/auth?client_id=<%=Iconstant.GOOGLE_CLIENT_ID%>&redirect_uri=<%=Iconstant.GOOGLE_REDIRECT_URI%>&response_type=code&scope=email profile"
                           class="btn btn-outline-danger btn-lg d-flex align-items-center justify-content-center"
                           style="width: 60px; height: 60px; border-radius: 12px; border-width: 2px;">
                            <i class="fab fa-google" style="font-size: 24px;"></i>
                        </a>

                        <a href="https://www.facebook.com/v18.0/dialog/oauth?client_id=<%=Iconstant.FACEBOOK_CLIENT_ID%>&redirect_uri=<%=Iconstant.FACEBOOK_REDIRECT_URI%>&scope=email"
                           class="btn btn-outline-primary btn-lg d-flex align-items-center justify-content-center"
                           style="width: 60px; height: 60px; border-radius: 12px; border-width: 2px;">
                            <i class="fab fa-facebook-f" style="font-size: 24px;"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="col-4 my-auto custom_bg--gray border rounded p-5">
        <div class="text-start my-auto">
            <div class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center">
                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/cart.svg" width="20px"
                     height="20px"/> <f:message key="signin.track_orders"/>
            </div>
            <div class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center">
                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/user.svg" width="20px"
                     height="20px"/> <f:message key="signin.manage_personal_info"/>
            </div>
            <div class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center">
                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/favorite.svg" width="20px"
                     height="20px"/> <f:message key="signin.create_wishlist"/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>